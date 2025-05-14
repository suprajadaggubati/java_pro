import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Clock extends JFrame {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;

    Clock() {
        // Frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setResizable(false);

        // Formats
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");

        // Time label
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 60));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setOpaque(true);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Day label
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        dayLabel.setForeground(Color.DARK_GRAY);
        dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Date label
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 28));
        dateLabel.setForeground(Color.DARK_GRAY);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel for all labels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add labels to panel
        mainPanel.add(timeLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // Spacer
        mainPanel.add(dayLabel);
        mainPanel.add(Box.createVerticalStrut(5));  // Spacer
        mainPanel.add(dateLabel);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null); // Center on screen
        this.setVisible(true);

        // Start timer
        startClock();
    }

    private void startClock() {
        // Update every second using Swing Timer
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
        updateTime(); // Initial call
    }

    private void updateTime() {
        Calendar now = Calendar.getInstance();
        timeLabel.setText(timeFormat.format(now.getTime()));
        dayLabel.setText(dayFormat.format(now.getTime()));
        dateLabel.setText(dateFormat.format(now.getTime()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Clock::new);
    }
}
