

import javax.swing.*;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.*;
import java.util.Date;


// New event listener that monitors changing values for components

public class Main extends JFrame {
    private JButton setTime, stop;
    private JLabel countdown, question, alarmtimer;
    private JTextField inputSecond, inputMin;
    private long looktime ;
    private Sound alarm = new Sound();
    private Timer  alarmer;
    private alarmListener a;


    public static void main(String [] args) throws Exception
    {
        //this will be the alarm clock
        new Main();
    }

    public Main()throws Exception{
        this.setSize(400,300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Timer");
        JPanel thePanel = new JPanel();


        //label for question
        question = new JLabel("How long does timer go for?");
        thePanel.add(question);
        //text field
        inputMin = new JTextField("00",2);
        inputMin.setToolTipText("for mins");
        inputMin.setVisible(true);
        thePanel.add(inputMin);
        inputSecond = new JTextField("00",2);
        inputSecond.setToolTipText("for seconds");
        inputSecond.setVisible(true);
        thePanel.add(inputSecond);


        ///button for starting timer
        setTime = new JButton("Set");
        ButtonListener bListener = new ButtonListener();
        setTime.addActionListener(bListener);
        thePanel.add(setTime);
        //button to stop the alarm
        stop = new JButton("Stop");
        stop.addActionListener(bListener);
        stop.setVisible(false);
        thePanel.add(stop);
        //label for alarmtimer will be invisible by default
        alarmtimer = new JLabel("lamo");
        alarmtimer.setVisible(false);
        alarmtimer.setForeground(Color.red);
        thePanel.add(alarmtimer);
        //operations for time countdown
        countdown = new JLabel("00.00.00");
        watch tListener = new watch();
        Timer t = new Timer(1000, tListener);
        t.setRepeats(true);
        t.start();
        thePanel.add(countdown);
        //panel stuff
        thePanel.setVisible(true);
        this.add(thePanel);
        this.setVisible(true);
        //this.setSize();
        this.setResizable(false);

    }



    private class ButtonListener implements ActionListener{
        private long min;
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == setTime) {
                try{
                    looktime = Long.parseLong(inputSecond.getText());
                    looktime = Long.parseLong(inputMin.getText())*60 + looktime;
                    min = Long.parseLong(inputMin.getText());
                }
                catch(Exception a){
                    JOptionPane.showMessageDialog( Main.this , "Input a number", "Error", JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    //set the labels to be visible
                    setTime.setVisible(false);
                    String s = Long.toString(looktime);
                    alarmtimer.setText(s);
                    alarmtimer.setVisible(true);
                    stop.setVisible(true);
                    //timer for the alarm
                    a = new alarmListener();
                    alarmer = new Timer(1000, a);
                    alarmer.setRepeats(true);
                    alarmer.start();
                    //alarm.stop();

                }

            }
            else if(e.getSource() == stop){
                alarmer.stop();
                alarm.stop();
                setTime.setVisible(true);
            }

        }
    }


    //action listener for alarm count down

    private class alarmListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            looktime--;
            String s = Long.toString(looktime);
            alarmtimer.setText(s);
            if(looktime <= 0){
                //sound queue
                alarm.play();
                //timer
                alarmer.stop();
            }
        }


    }

    //acition litnsener for timer
    private class watch implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Date d= new Date();

            String s = d.toString();
            s = s.substring(10, 20);
            countdown.setText(s );
        }

    }







}





