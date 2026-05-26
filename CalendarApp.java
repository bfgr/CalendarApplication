import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;

public class CalendarApp {

    private JFrame frame;
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private YearMonth currentMonth;

    public CalendarApp() {

        currentMonth = YearMonth.now();

        frame = new JFrame("Calendar Application");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();

        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");

        monthLabel = new JLabel("", SwingConstants.CENTER);

        prevButton.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            updateCalendar();
        });

        nextButton.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            updateCalendar();
        });

        topPanel.add(prevButton);
        topPanel.add(monthLabel);
        topPanel.add(nextButton);

        frame.add(topPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0,7));

        frame.add(calendarPanel, BorderLayout.CENTER);

        updateCalendar();

        frame.setVisible(true);
    }

    private void updateCalendar() {

        calendarPanel.removeAll();

        monthLabel.setText(
                currentMonth.getMonth() + " " + currentMonth.getYear()
        );

        String[] days = {
                "Mon","Tue","Wed",
                "Thu","Fri","Sat","Sun"
        };

        for(String day : days){
            calendarPanel.add(
                    new JLabel(day, SwingConstants.CENTER)
            );
        }

        LocalDate firstDay = currentMonth.atDay(1);

        int startDay = firstDay.getDayOfWeek().getValue();

        for(int i=1;i<startDay;i++){
            calendarPanel.add(new JLabel(""));
        }

        for(int day=1; day<=currentMonth.lengthOfMonth(); day++){

            JButton button = new JButton(String.valueOf(day));

            LocalDate today = LocalDate.now();

            if(day==today.getDayOfMonth() &&
                    currentMonth.equals(
                            YearMonth.from(today))){

                button.setBackground(Color.GREEN);
            }

            calendarPanel.add(button);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new CalendarApp()
        );
    }
}