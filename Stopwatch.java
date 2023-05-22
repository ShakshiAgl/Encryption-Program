import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");

    // holds the current time
    JLabel timeLabel = new JLabel();

    // holds the amount of milliseconds after we start our timer
    int elapsedTime = 0;

    // holds the amount of seconds that have passsed
    int seconds = 0;

    // holds the amount of minutees that have passed
    int minutes = 0;

    // holds the amount of hours that have passed
    int hours = 0;

    // determines that our timer has started or not
    boolean started = false;

    // displaying the format of second, minute and hours
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    // the timer updates every 1000 milliseconds i.e. 1 seconds
    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;

            // 360,000 is the amount of milliseconds in 1 hour
            hours = (elapsedTime / 3600000);

            // 60,000 is the amount of milliseconds in 1 minute
            minutes = (elapsedTime / 60000) % 60;

            // 1000 is the amount of milliseconds in 1 second
            seconds = (elapsedTime / 1000) % 60;

            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);

            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });

    // constructor for stopwatch class
    Stopwatch() {

        // displaying time
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        // displaying start button
        startButton.setBounds(100, 200, 100, 50);
        startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        // displaying reset button
        resetButton.setBounds(200, 200, 100, 50);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            start();
            if (started == false) {
                started = true;
                startButton.setText("STOP");
                start();
            } else {
                started = false;
                startButton.setText("START");
                stop();
            }
        }
        if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("START");
            reset();

        }
    }

    // method to start the watch
    void start() {
        timer.start();

    }

    // method to stop the watch
    void stop() {
        timer.stop();

    }

    // method to reset the watch
    void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        // updating our time
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);

    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
    }

}
