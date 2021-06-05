package calendar;

public class MyCalendar {
    private int year = 2019;
    private int[] day = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int counterDate = 1;
    private String[] month = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private int counterMonth = 6;
    private String[] week = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private int dayWeek = 0; // 0 - monday, 1- tuesday...
    private int lastWorkDay = 0; // last buss. day

    public void getDate() {
        System.out.println(week[dayWeek]);
        System.out.println(month[counterMonth] + ", " + counterDate + " " + year);
    }

    public boolean setNewDate() {
    counterDate+=1;
        if (counterDate > day[counterMonth]) {
            counterDate = 1;
            if (counterMonth + 1 > 12) {
                year += 1;
                if(isLeapYear(year))
                    day[1] = 29;
                else
                    day[1] = 28;
            }
            counterMonth = (counterMonth + 1) % 12;
            setLastWorkDay();
            return true;
        }
        dayWeek = (dayWeek + 1) % 7;
        return false;
    }



    public void setLastWorkDay() {
        int lastDay = day[counterMonth];
        String dayOfLast = week[(dayWeek + lastDay) % 7];

        if (dayOfLast.equals("Saturday")) lastDay -= 1;
        else if (dayOfLast.equals("Sunday")) lastDay -= 2;
        lastWorkDay = lastDay;
    }

    private boolean isLeapYear(int year) {
        if(year >= 1 && year <= 9999){
            if(year % 4 == 0){
                if(year % 100 == 0){
                    if(year % 400 == 0){
                        return true;
                    }
                    else return false;
                }
                else return true;
            }
            else return false;
        }
        else
            return false;
    }

    public int getDayWeek(){
        return dayWeek;
    }

    public int getLastWorkDay(){
        return lastWorkDay;
    }

    public int getCounterDate() {
        return counterDate;
    }

    public String getMonth(){
        return month[counterMonth];
    }

}




