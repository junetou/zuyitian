package org.andy.work.utils;

import java.math.BigDecimal;

public class MathUtil {
	
	public static Integer toIntValue(Double value)
	{
		if (value == null)
		{
			return 0;
		}
		
		return new BigDecimal(value).setScale(0, BigDecimal.ROUND_DOWN).intValue();
	}
	
	public static Integer toIntValue(String value)
	{
		if (!StringUtil.hasValue(value))
		{
			return 0;
		}
		
		return new BigDecimal(StringUtil.trim(value)).setScale(0, BigDecimal.ROUND_DOWN).intValue();
	}
	
	public static BigDecimal add(Double d1, Double d2)
	{
		return new BigDecimal(d1).add(new BigDecimal(d2));
	}
	
	public static BigDecimal add(Double d1, Double d2, Integer scale)
	{
		return new BigDecimal(d1).add(new BigDecimal(d2)).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal add(BigDecimal b1, BigDecimal b2)
	{
		return b1.add(b2);
	}
	
	public static BigDecimal round(Double d1, Integer scale)
	{
		return new BigDecimal(d1).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal multiply(Double d1, Double d2, Integer scale)
	{
		return new BigDecimal(d1).multiply(new BigDecimal(d2)).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal multiply(Double d1, Double d2)
	{
		return new BigDecimal(d1).multiply(new BigDecimal(d2));
	}
	
	public static BigDecimal divide(double d1, double d2)
	{
		return new BigDecimal(d1).divide(new BigDecimal(d2));
	}
	
	public static BigDecimal divide(double d1, double d2, int scale)
	{
		return new BigDecimal(d1).divide(new BigDecimal(d2), scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal subtract(Double d1, Double d2)
	{
		return subtract(d1, d2, null);
	}
	
	public static BigDecimal subtract(Double d1, Double d2, Integer scale)
	{
		BigDecimal b = new BigDecimal(d1).subtract(new BigDecimal(d2));
		if (scale != null)
		{
			b.setScale(scale, BigDecimal.ROUND_HALF_UP);
		}
		
		return b;
	}
	
}
