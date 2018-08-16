package com.ubs.opsit.interviews;

import java.util.Arrays;

public class BerlinClock implements TimeConverter{

	@Override
	public String convertTime(String aTime) {
		int[] parts = Arrays.asList(aTime.split(":")).stream().mapToInt(Integer::parseInt).toArray();
		StringBuilder clockTime = new StringBuilder();
		clockTime.append(getSeconds(parts[2])).append("\r\n").append(getTopHours(parts[0])).append("\r\n").append(getBottomHours(parts[0])).append("\r\n").append(getTopMinutes(parts[1])).append("\r\n").append(getBottomMinutes(parts[1]));
        return clockTime.toString();
    }
	
	public static void main(String[] args) {
		TimeConverter tm = new BerlinClock();
		System.out.println("11:46:33");
		System.out.println(tm.convertTime("11:46:33"));
	}

    public String getSeconds(int number) {
        if (number % 2 == 0) return "Y";
        else return "O";
    }

    public String getTopHours(int number) {
        return getOnOff(4, getTopNumberOfOnSigns(number));
    }

    public String getBottomHours(int number) {
        return getOnOff(4, number % 5);
    }

    public String getTopMinutes(int number) {
        return getOnOff(11, getTopNumberOfOnSigns(number), "Y").replaceAll("YYY", "YYR");
    }

    public String getBottomMinutes(int number) {
        return getOnOff(4, number % 5, "Y");
    }

    // Default value for onSign would be useful
    private String getOnOff(int lamps, int onSigns) {
        return getOnOff(lamps, onSigns, "R");
    }
    private String getOnOff(int lamps, int onSigns, String onSign) {
        String out = "";
        // String multiplication would be useful
        for (int i = 0; i < onSigns; i++) {
            out += onSign;
        }
        for (int i = 0; i < (lamps - onSigns); i++) {
            out += "O";
        }
        return out;
    }

    private int getTopNumberOfOnSigns(int number) {
        return (number - (number % 5)) / 5;
    }


}
