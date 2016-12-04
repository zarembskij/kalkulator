import java.text.DecimalFormat;

public class Memory {
	
	{
	
	}
	
	protected String sDigit;
	protected String sEquals;
	protected String sOperator = "n";
	protected boolean bIsResult = false;
	protected double dDigit;
	protected double dDigit2;
	protected boolean bPointIsSet = false;
	
	DecimalFormat df = new DecimalFormat("#.##########");

	protected Memory(String sDigit, double dDigit, double dDigit2)
	{
		this.sDigit = sDigit;
		this.dDigit = dDigit;
		this.dDigit2 = dDigit2;
	}
	protected String addDigit(String digit)
	{
		
		if (sOperator == "=")
		{
			if (digit !=",")
			{
				
				sDigit = digit;	
				dDigit = Double.parseDouble(sDigit.replace(",", "."));
				bPointIsSet = false;
			}
			else 
			{
				sDigit = "0,";	
				bPointIsSet = true;
				
			}
			sOperator = "n";
			dDigit2 = 0;
		}
		
		if (sDigit != "0" && bIsResult && digit !=",")
		{
			sDigit += digit;
			dDigit = Double.parseDouble(sDigit.replace(",", "."));
		}
		else if (sDigit == "0" && digit !=",")
		{
			sDigit = digit;
			dDigit = Double.parseDouble(sDigit.replace(",", "."));
		}
		else if (digit == "," && !bPointIsSet)
		{
			sDigit+=digit;
			bPointIsSet = true;
		}
		
		bIsResult = true;
	
		return sDigit;
	}

	protected String resultCurr(String current)
	{
		
		bPointIsSet = false;
		
		try
		{
			if (sOperator == "=")
			{
				switch (current)
				{
					case "sq":
					{
						if (dDigit2 < 0)
							throw new ArithmeticException("ERR");
						dDigit2 = Math.sqrt(dDigit2);
						sEquals = df.format(dDigit2);
						break;
	
					}
					case "pr":
					{
						dDigit2 = dDigit2/100;
						sEquals = df.format(dDigit2);	
						break;
					}
					case "ab":
					{
						dDigit2 = dDigit2*(-1);
						sEquals = df.format(dDigit2);
						break;
					}
					case "xx":
					{
						if (dDigit2 == 0)
							throw new ArithmeticException("ERR");
						dDigit2 = 1/dDigit2;
						sEquals = df.format(dDigit2);
						break;
					}
				}
			}
			else
			{
				switch (current)
				{
					case "sq":
					{
						if (dDigit < 0)
							throw new ArithmeticException("ERR");
						dDigit = Math.sqrt(dDigit);
						sEquals = df.format(dDigit);
						break;
					
					}
					case "pr":
					{
						dDigit = dDigit/100;
						sEquals = df.format(dDigit);	
						break;
					}
					case "ab":
					{
						dDigit = dDigit*(-1);
						sEquals = df.format(dDigit);
						break;
					}
					case "xx":
					{
						if (dDigit == 0)
							throw new ArithmeticException("ERR");
						dDigit = 1/dDigit;
						sEquals = df.format(dDigit);
						break;
					}
				}
			}
			
		}
		catch (Exception e) {
				sEquals = "ERR";
				
		}
		
		sDigit = "0";
		return sEquals;
	}

	
	protected void addOperator(String operator)
	{
		bPointIsSet = false;
		
		try
		{
			switch (sOperator)
			{
			case "n":
				dDigit2 = dDigit;
				break;
			case "+":
				dDigit2 += dDigit;
				break;
			case "-":
				dDigit2 -= dDigit;
				break;
			case "*":
				dDigit2 = dDigit * dDigit2;
				break;
			case "/":
			
				if (dDigit == 0)
				{
					throw new ArithmeticException("ERR");
				}
				dDigit2 = dDigit2 / dDigit;
				break;
			}
		}
		catch (Exception e) {
		
			sEquals = "ERR";
		}

		dDigit = 0;
		sOperator = operator;
		bIsResult = false;
		

	}
	
	protected String result()
	{
		bPointIsSet = false;
		if (sEquals != "ERR")
		{	
			sEquals = df.format(dDigit2);

			sDigit = "0";
			return sEquals;
		}
		else
		{
			return sEquals;
		}
	}
	
	protected void clearMemory()
	{
		sDigit = "0";
		dDigit = 0;
		dDigit2 = 0;
		sEquals="0";
		bIsResult = false;
		sOperator = "n";
		bPointIsSet = false;
	}

	public void tmpPisz()
	{
		
		System.out.println(sDigit + " " + dDigit + " "+ String.valueOf(dDigit2) + " "+ sEquals + " " + sOperator + " " + bPointIsSet);

	}

}
