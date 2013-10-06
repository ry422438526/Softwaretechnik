package cn.com.superv.Bowlingscore;

import java.util.ArrayList;

public class BowlingScore {

	public static final int NA = -1;
	public static final int FRAME_COUNT = 10;
	int p[] = new int[] { NA, NA, NA, NA, NA, NA, NA, NA, NA, NA };
	public ArrayList<Integer> s = new ArrayList<Integer>();

	public int[] getAccumulatedScores() {
		int t[] = new int[] { NA, NA, NA, NA, NA, NA, NA, NA, NA, NA };
		calculate();
		int len = getCurrentFrameNumber();
		for (int i = 0; i < len; i++) {
			if(p[i] == NA)
			{
				break;
			}
			else
			{
				if(i == 0)
				{
					t[i] = p[i];
				}
				else
				{
					t[i] = t[i-1] + p[i];
				}
			}
		}
		return t;

	}

	private void calculate() {
		int m = 0;
		for (int j = 0; j < s.size(); j++) {
			if((j + 1) > s.size()-1)
			{
				break;
			}
			if (s.get(j) == 10)
			{
				if((j+2)>s.size()-1)
				{
					break;
				}
				else
				{
					p[m++] = s.get(j) + s.get(j + 1)+ s.get(j + 2);
					continue;
				}
			}
			else
			{
				if(s.get(j) + s.get(j + 1) < 10)
				{
					p[m++] = s.get(j) + s.get(j + 1);
					j++;
					continue;
				}
				else
				{
					if((j+2)>s.size()-1)
					{
						break;
					}
					else
					{
						p[m++] = 10 + s.get(j + 2);
						j++;
						continue;
					}
				}
			}
		}
	}

	public int getCurrentFrameNumber() {
		int falg = 1; // 表示每小局是否第一球。
		int CurrentNumber = 0;
		for (int i = 0; i < s.size(); i++) {
			if (s.get(i) == 10 || falg == 2) {
				if(CurrentNumber >= 10)
				{
					break;
				}
				CurrentNumber++;
				falg = 1;
			} else
				falg = 2;
		}
		return CurrentNumber;
	}

	public void pinDown(int i) {
		s.add(i);
	}

	public void pinDown(int[] pins) {
		for (int i = 0; i < pins.length; i++)
			s.add(pins[i]);
	}

	public int getScore(int i) {
		calculate();
		return p[i];
	}

}
