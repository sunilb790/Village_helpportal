import java.util.*;
import java.io.*;
class MyException extends Exception       //Exception Handling
{
	MyException(String str){
		super(str);
	}
}
class EraserThread implements Runnable {         //class which implements Runnble interface
	   private boolean stop;
	   public EraserThread(String prompt) {
	       System.out.print(prompt);
	   }
	   public void run () {                 //overriding run() function
	      stop = true;
	      while (stop) {
	         System.out.print("\010*");
		 try {
		     Thread.currentThread();
	         Thread.sleep(100);
	         } catch(InterruptedException ie) {    //to catch the exception thrown
	            ie.printStackTrace();
	         }
	      }
	   }
	   public void stopMasking() {     //function to stop masking
	      this.stop = false;
	   }
	}
	class Password{
	    public static String readPassword (String prompt) {
	       EraserThread et = new EraserThread(prompt);
	       Thread mask = new Thread(et);
	       mask.start();             //to start the thread
	        //to read the input from console
	       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	       String password = "";
	 
	       try {
	          password = in.readLine();     //read the input 
	       } catch (IOException ioe) {
	         ioe.printStackTrace();
	       }    
	       et.stopMasking();      // stop masking	       
	       return password;      // return the password entered by the user
	    }
	 }
abstract class Customer                //abstract class of customer i.e can't be instantiated
{
    Scanner sc=new Scanner(System.in);
	protected String username;
	protected String userid;
	protected String tehsil;
	public void registration() throws IOException     //function for registration 
	{
		boolean choice1=true;
		boolean choice2=true;
	    System.out.println("");	
	    try {
           FileWriter file1=new FileWriter("GraminSewaUserName.txt",true);  //filewriting to store the username in append mode
           while(choice1)
           {
        System.out.println("Please enter the name to register in Gramin Sewa Portal.");
	username=sc.nextLine();
	System.out.flush();	
    FileReader f=new FileReader("GraminSewaUserName.txt");    //to read the file
	char array1[]=new char[1000];
	int ch,i=0;
	while((ch=f.read())!=-1)
{
	array1[i]=(char)ch;
	i++;
        }
	f.close();
        String str1=new String(array1);
        boolean t=(str1.contains(username)==true);    //to check that user is already registered or not
        	if(t==true)
        	{
        		System.out.println();
        		System.out.println("This username is already Registered. Please Try Something else!!!");
        	}
        	else
        	{
	file1.write(username);	       //to write in file
	choice1=false;
        	}
           }
	FileWriter file2=new FileWriter("GraminSewaPassWord.fcx",true);   //filewriting to store user password
	while(choice2)
	{
	System.out.println("Please enter a password for protection.");
	userid=Password.readPassword("password: ");           //read password using masking
        System.out.flush();
        try {
        if(userid.length()<6)        // password should not less than 6 digit
        {
        	throw new MyException("Password should not be less than 6 digits");
        }
        else
        {
        	file2.write(userid);    //to write in file
        	choice2=false;
        }}
        catch(MyException e)          // to catch exception thrown by read(),write() and all
        {
        	System.out.println("Exception caught: "+e.getMessage());
        }
	}
        System.out.println("Please enter your Tehsil");
        tehsil=sc.nextLine();
    FileWriter file3=new FileWriter("GraminSewaTehsil.txt",true);  // filewriting to store the tehsil of user
    file3.write(tehsil);

	file1.close();        
	file2.close();
	file3.close();
	    }
	    catch(IOException e)
	    {
	    	System.out.println("Exception caught: "+e.getMessage());
	    }
        System.out.println("");
	System.out.println("!!!!*********You have sucessfully registered in Gramin Sewa portal.*********!!!!");
	System.out.println("!!!!****Thank you****!!!!");
	System.out.println("");
	}
     public void login() throws IOException      //login function for customer               
    {
    boolean c=true;
    try {
    while(c)
	{
    	System.out.println("!!!!***** LOGIN PAGE *****!!!!");
    	System.out.println("");
	System.out.println("Please enter the username:-");
	String s1=sc.nextLine();
	sc.nextLine();
	System.out.flush();
	FileReader fr1=new FileReader("GraminSewaUserName.txt");   //file read to match form stored file
	char array1[]=new char[1000];
	int ch,i=0;
	while((ch=fr1.read())!=-1)
{
	array1[i]=(char)ch;
	i++;
        }
	fr1.close();
        String str1=new String(array1);        
	System.out.println("Please enter the userid:-");
	String s2=Password.readPassword("PassWord: ");
	System.out.flush();
	FileReader fr2=new FileReader("GraminSewaPassWord.fcx");
	char array2[]=new char[1000];
	int cha,i2=0;
	while((cha=fr2.read())!=-1)
{
	array2[i2]=(char)cha;
	i2++;
        }
	fr2.close();
        String str2=new String(array2);
	boolean t=(str1.contains(s1)==true &&str2.contains(s2)==true);
	if(s2.length()>=6)
	{	
	    if(t==true)
	    {
	        System.out.println("");
		System.out.println("!!!!!********WELCOME TO GRAMIN SEWA PORTAL********!!!!!");
		break;
}
	else
	{
		System.out.println("Sorry!!! Either username or userid is incorrect!!! Please try again");
		System.out.println("");
	System.out.println("Do you want to continue?(true/false)");
		c=sc.nextBoolean();
		if(c==false)
		System.exit(0);
	}
	}
	else
	{
		System.out.println("Sorry!!! Either username or userid is incorrect!!! Please try again");
		System.out.println("");
	System.out.println("Do you want to continue?(true/false)");
		c=sc.nextBoolean();
		if(c==false)
		System.exit(0);
	}
}
	}
    catch(IOException e)
    {
		System.out.println("Exception caught: "+e.getMessage());
		System.exit(1);
}
}
}
interface I1{                      //interface for applications of portal 
    public void Agriculture(); 
    public void VillageDevelop();
    public void Sanitation();
    public void Health();
	public void Education();
	public void CityHelp();
	public void BusinessGuide();
    }
class Application extends Customer implements I1         //class for applications for portal 
{
    public void Agriculture()              //function related with agriculture issues
    {
    	 Scanner sc=new Scanner(System.in);    	       
         System.out.println("////******THIS PAGE IS RELATED WITH AGRICULTURAL PROBLEMS FACED BY FARMERS AND THEIR SOLUTION******\\\\\\\\");
		System.out.println("");
		boolean choice=true;
		while(choice)
		{
        System.out.println("********What do You want to know about??****");
		System.out.println("");
        System.out.println("1.Seeds");
        System.out.println("2.FERTILIZERS");
        System.out.println("3.Irrigation");
        System.out.println("4.Crop Management");
        System.out.println("5.You can ask questions from our experts");
        System.out.println("");
        System.out.println(" Please Enter your choice");
		int ch=sc.nextInt(); 
        switch(ch)
        {
           case 1:
           this.Seeds();       
        break;
           case 2:
        this.Fertilisers();   
        break;
        case 3:
       this.Irrigation();     
        break;
        case 4:
       this.CropManagement();
       break;
        case 5:
        System.out.println("PLEASE TYPE YOUR QUESTION HERE");    //for any special query
		String str=sc.nextLine();
		sc.nextLine();
        System.out.println("Your question has sent to Experts you will get your answer shortly"); 
        break;
        default:
        System.out.println("Sorry!! Invalid choice");
	}
	System.out.println();
    System.out.println("Do you want to continue in the same(Agriculture) page(true/false)");
    choice=sc.nextBoolean();
    }
}
   private void Seeds()   //Seeds function definition
    {    	
       		 System.out.println("WELCOME TO SEED fACILITY PAGE");
    		 System.out.println("FACILITIES");
    		 System.out.println("1. Distribution");
    		 System.out.println("2. Quality");
    		 System.out.println("Enter your choice");
    		 int k=sc.nextInt();
    		 switch (k)
    		 {
    		 case 1:
    			 this.distribution();
    			 break;
    		 case 2:
    			 	this.quality();
    			 	break;
    	     default:
    	    	 System.out.println("Please enter a valid choice");
    		 }
    	 }
    private void distribution()
    {
		try {
    		int c;
    		BufferedReader file5=new BufferedReader (new FileReader("Seeddistribution.txt"));
    		while((c=file5.read())!=-1)
    		{
    			System.out.print((char)c);
    		
    		}
    		file5.close();
    	}catch(IOException e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
    private  void quality()
    {
    	try {
        	int c;
        	BufferedReader file6=new BufferedReader (new FileReader ("Seedquality.txt"));
        	while((c=file6.read())!=-1)
        	{
        		System.out.print((char)c);
        		
        	}
        	file6.close();
        }catch(IOException e)
        {
        	System.out.println(e.getMessage());
        }

    }
    private void Fertilisers()
    {
   		 System.out.println("WELCOME TO FERTILISER fACILITY PAGE");
   		 System.out.println("FACILITIES");
   		 System.out.println("1. Distribution");
   		 System.out.println("2. Availability");
   		 System.out.println("Enter your choice");
   		 int k=sc.nextInt();
   		 switch (k)
   		 {
   		 case 1:
   			 this.ferdistribution();
   			 break;
   		 case 2:
   			 	this.availability();
   			 	break;
   		 }
   	 }   	
    private void ferdistribution()
    {
    	try {
    		int c;
    		BufferedReader file1=new BufferedReader (new FileReader("Fertiliserdistribution.txt"));
    		while((c=file1.read())!=-1)
    		{
    			System.out.print((char)c);
    		}
    		file1.close();
    	}catch(IOException e)
    {
    	System.out.println(e.getMessage());
    }
    }   
    private void availability()
    {
    	try {
    		int c;
    		BufferedReader file7=new BufferedReader (new FileReader("Fertiliseravailability.txt"));
    		while((c=file7.read())!=-1)
    		{
    			System.out.print((char)c);
    		}
    		file7.close();
    	}catch(IOException e)
    {
    	System.out.println(e.getMessage());
    }
    	
    	System.out.println(" *For any other  query related to use of fertiliser go back contact to our experts");
    }
    private void Irrigation()
    {
    	System.out.println("WELCOME TO IRRIGATION RELATED fACILITY PAGE");
  		 System.out.println("FACILITIES");
  		 System.out.println("1. Water Management");
  		 System.out.println("2.Instructions");
  		 System.out.println("Enter the choice");
  		 int c=sc.nextInt();
  		 switch(c)
  		 {
  		 case 1:
  			 this.waterManagement();
  			 break;
  		 case 2:
  			 this.IrrInstructions();
  			 break;
  		 }
    }
    private void waterManagement()
    {
    	try {
			int c;
			BufferedReader file8=new BufferedReader(new FileReader("Irrigation.txt"));
			
			while((c=file8.read())!=-1)
			{
				System.out.print((char)c);
			}
			file8.close();
		}catch(IOException e)
	{
		System.out.println(e.getMessage());
	}

    }
    private void IrrInstructions()
    {
		try {
			int c;
			BufferedReader file9=new BufferedReader(new FileReader("IrrigationAdvice.txt"));
			
			while((c=file9.read())!=-1)
			{
				System.out.print((char)c);
			}
			file9.close();
		}catch(IOException e)
	{
		System.out.println(e.getMessage());
	}
   
    }
    private void CropManagement()
    {
    	System.out.println("WELCOME TO CROP MANAGEMENT RELATED fACILITY PAGE");
 		 System.out.println("FACILITIES");
 		 System.out.println("1.Storage");
 		 System.out.println("2.Transportation");
 		 System.out.println("Enter the choice");
 		 int t=sc.nextInt();
 		 switch(t)
 		 {
 		 case 1:
 			 this.Storage();
 			 break;
 		 case 2:
 			 this.Transportation();
 			 break;
 		 }
    }
    private void  Transportation()
    {
    	System.out.println("TRANSPORT FACILITIES FOR FARMERS");
       	System.out.println("Please note that these all transports are available to your nearest Fields During Harvesting season");
       	System.out.println("1.Tractor");
       	System.out.println("2.Bullock cart");
       	System.out.println("For packaging materials contact to your nearest help centres.");
       	System.out.println("");
    }
    private void Storage()
    {
		try {
			int c;
			BufferedReader file10=new BufferedReader(new FileReader("Storage.txt"));
			
			while((c=file10.read())!=-1)
			{
				System.out.print((char)c);
			}
			file10.close();
		}catch(IOException e)
	{
		System.out.println(e.getMessage());
	}

    }
    public void VillageDevelop()
    {
		System.out.println("***** THIS PAGE IS RELATED WITH VILLAGE DEVELOPMENT*****");
		System.out.println();
		boolean b=true;
        while(b) {
		System.out.println("We are here to help you for development issues.");
        System.out.println("1. Road Development Issues");
        System.out.println("2. Sewage Development Issues");
        System.out.println("3. Others ");
        System.out.println("Enter your choice "); 
        int m=sc.nextInt();
        switch(m) {
        
        case 1:
        	this.RoadDevelopment();
        	break;
        	
        case 2:
        	this.SewageDevelopment();
        	break;
        case 3:
        	System.out.println("FOR OTHER QUERIES ");
        	System.out.println("TYPE YOUR QUERY HERE");
        	sc.nextLine();
        	String str=sc.nextLine();
        	try {
        		FileWriter file2=new FileWriter("Complaints.txt");
        		file2.write(str);
        		file2.close();
        	}catch(IOException e)
        	{
        		System.out.println(e.getMessage());
        	}
        	break;
        default:
        	System.out.println("INVALID CHOICE !!!");
        }
        System.out.println("Do you want to continue in same(Village develop page).(true/false)");
		 b=sc.nextBoolean();
		 System.out.println();
        }
    }
        
       private void RoadDevelopment()
        {
    	   System.out.println("Road Development Committee visits once after 3 months  ");
    	   System.out.println("Report Your Problems Here");
		   String str =sc.nextLine();
		   sc.nextLine();
			System.out.println("One of Committee Members will Reach you soon");
			System.out.println();
        }
       private void SewageDevelopment() {
    	   
    	   System.out.println("Sewage Treatment Team visits once After 2 months");
    	   System.out.println("Report Your Problems Here");
		   String str =sc.nextLine();
		   sc.nextLine();
			System.out.println("One of Committe Members will Reach you soon");
			System.out.println();
       }
    public void Sanitation()
    {
		System.out.println("***** THIS PAGE IS RELATED WITH VILLAGE SANITATION ISSUES*****");
		System.out.println();
		boolean b=true;
		while(b)
        {
		System.out.println("Village Sanitation Help Center Welcomes you!!");
        System.out.println("How Can We Help You");
        System.out.println("1.Regarding Building A Toilet ");
        System.out.println("2.Regarding Cleanliness Of Toilets");
        System.out.println("Specify Your Query");
        int m=sc.nextInt();
        	switch(m)
        	{
        	case 1:
        		BuildToilet();
        		break;
        	case 2:
        		CleanToilet();
        		break;
        	default:
        		System.out.println("Invalid Choice!!");
        	}
        	System.out.println("Do you want to continue in same(Sanitation page).(true/false)");
			 b=sc.nextBoolean();
			 System.out.println();
        }
          
    }
    
    private void BuildToilet()
	{
		System.out.println("Enter Your Address Here");
		String str=sc.nextLine();
		sc.nextLine();
		System.out.println("Out team will reach you to inspect area Soon!!");
		System.out.println();
	}
	private void CleanToilet()
	{
		System.out.println("Our Cleanliness Team visit 3 Days in a week");
		System.out.println("You can Give Your FeedBack Here ");
		String str=sc.nextLine();
		sc.nextLine();
		System.out.println("You can Also Mark us OUT OF 10");
		int n=sc.nextInt();
		if(n<=5)
		{
			System.out.println("Sorry for Inconvenience, We will reach you as soon as possible");
		}
		else if(n>6 && n<=8)
		{
			System.out.println("Thanks For Your FeedBack, We will improve our facilities");
		}
		else if(n>=9 && n<=10)
		{
			System.out.println("THANKS FOR YOUR FEEDBACK");
		}
	}

    public void Health()
    {
		System.out.println("***** THIS PAGE IS RELATED WITH HEALTH ISSUES OF VILLAGERS *****");
		System.out.println();
		boolean b=true;
		while(b) {
		System.out.println("Health Team welcomes You");
    	System.out.println("1. Hospital Issues");
    	System.out.println("2. Medicine Issues");
    	System.out.println("3. Doctor's Advice ");
    	System.out.println("4. Enter your choice");
    	int c=sc.nextInt();    	
    		switch(c)
    		{
    		case 1:
    			HospitalIssues();
    			break;
    		case 2:
    			MedicineIssues();
    			break;
    		case 3:
				DoctorAdvice();
				break;
    		default:
    			System.out.println("INVALID CHOICE");
			}
			System.out.println("Do you want to continue in same(Health Page).(true/false)");
			b=sc.nextBoolean();
			System.out.println();
    	}
    	
    }
   
    public  void HospitalIssues (){
    {
    	System.out.println("We Are Here to help you with Hospital Issues");
    	System.out.println("Press 1 to see Hospitals Details ");
    	int Hospital=sc.nextInt();
    	if(Hospital==1) {
    	try {
    		int ch;
    	BufferedReader file1=new BufferedReader(new FileReader("GraminSewaHospitalTimings.txt"));
    	while((ch=file1.read())!=-1) {
    		System.out.print((char)ch);
    	}
    	file1.close();
    	}catch(IOException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	}
    	else
    	{
    		System.out.println("THANK YOU FOR VISITING US");
    		System.exit(0);
    	}
    }
    
    }
    private void MedicineIssues()
    {
    	System.out.println("Welcome To Medicine Related Issues");
    	System.out.println("Press 1 to know more about Medicines");
    	int Medicine=sc.nextInt();
    	if(Medicine==1)
    	{
    		try {
    		int ch;
    		BufferedReader file2=new BufferedReader(new FileReader("Medicineinfo.txt"));
    		while((ch=file2.read())!=-1)
    		{
    			System.out.print((char)ch);
    		}
    		file2.close();
    		}catch(IOException e)
    		{
    			System.out.println(e.getMessage());
    		}
    		}
    	else
    	{
    		System.out.println("Thank YOU For visiting Us");
    	}
    	
    	}
    
    private void DoctorAdvice()
    {
    	System.out.println("ASK YOUR QUERIES TO DOCTORS");
		String st=sc.nextLine();
		sc.nextLine();
		System.out.println("YOUR QUERY IS RECORDED CONCERNED DOCTOR WILL GUIDE YOU SOON");
		System.out.println();
    }
    public void Education()
    {
		System.out.println("***** THIS PAGE IS RELATED WITH EDUCATION FACILITIES FOR VILLAGERS ******* ");
		boolean b=true;
		while(b)
    	{
		System.out.println("WE WELCOME YOU TO EDUCATION PAGE");
    	System.out.println("1. SchoolRelated");
    	System.out.println("2. StationaryRelated");
    	System.out.println("ENTER YOUR CHOICE");
    	int ch=sc.nextInt();
    		switch(ch)
    		{
    		case 1:
    			School();
    			break;
    		case 2:
    			Stationary();
    			break;
    		default:
    			System.out.println("INVALID CHOICE");   			
			}
			System.out.println("Do you want to continue in same(Education Page).(true/false)");
			b=sc.nextBoolean();
    	}
    }
    private void School()
    {
    	System.out.println("WELCOME TO SCHOOL PAGE");
    	System.out.println("Press 1 to know About ALL Schools ");
    	System.out.println("Press 2 Report Your School Related Problems");
    	int ch=sc.nextInt();
    	if(ch==1)
    	{
    		try {
    			int c;
    			BufferedReader file3=new BufferedReader(new FileReader("schoolinfo.txt"));
    			while((c=file3.read())!=-1)
    			{
    				System.out.print((char)c);
    			}
    			file3.close();
    		}catch(IOException e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
    	else if(ch==2)
    	{
    		System.out.println("Enter your Problem Here");
			String str=sc.nextLine();
			sc.nextLine();
    		System.out.println("Our concerned Team will reach you soon");
    	}
    }
    
    private void Stationary()
    {
    	System.out.println("Welcome To Stationary Page");
    	System.out.println("1. Books Availability ");
    	System.out.println("2. Report a Problem");
    	int ch=sc.nextInt();
    	if(ch==1)
    	{
    		try {
    			
                int c;		
    			BufferedReader file4=new BufferedReader(new FileReader("Booksandstationary.txt"));
    			while((c=file4.read())!=-1)
    			{
    				System.out.print((char)c);
    			}
    			file4.close();
    			
    		}catch(IOException e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
    	else if(ch==2)
    	{
    		System.out.println("Enter Your Complaints Here");
			String s=sc.nextLine();
			sc.nextLine();
    		System.out.println("OUR ACTION TEAM WILL REACH YOU SOON");
    	}
	}
	public void CityHelp()
	{
		System.out.println("***** THIS PAGE IS FOR SMALL SCALE BUSINESS AND PRODUCTION FOR VILLAGERS*****");
		System.out.println();
	     boolean c=true;
		while(c)
		{
		System.out.println("1. Dairy Business");
		System.out.println("2. Pottery Business");
		System.out.println("3. Grocery Business");
		System.out.println("CHOOSE YOUR BUSINESS TYPE");
		int b=sc.nextInt();
			switch(b)
			{
			case 1:
				DairyBusiness();
				System.out.println("Still Queries");
				System.out.println("Press 1 for YES ELSE Press 0");
				int m=sc.nextInt();
				if(m==1)
				{
					System.out.println("ASK YOUR QUESTIONS HERE");
					String s=sc.nextLine();
					sc.nextLine();
					System.out.println("GOT YOUR QUERY, OUR EXPERTS HELP YOU SOON");
					break;
				}
				else if(m==0)
				{
					System.out.println("THANK YOU!!! HOPE WE HELPED YOU");
					break;
				}
				
				break;
			case 2:
				PotteryBusiness();
				System.out.println("Still Queries");
				System.out.println("Press 1 for YES ELSE Press 0");
				int N=sc.nextInt();
				if(N==1)
				{
					System.out.println("ASK YOUR QUESTIONS HERE");
					String st=sc.nextLine();
					sc.nextLine();
					System.out.println("GOT YOUR QUERY, OUR EXPERTS HELP YOU SOON");
					break;
				}
				else if(N==0)
				{
					System.out.println("THANK YOU!!! HOPE WE HELPED YOU");
					break;
				}
				break;
			case 3:
				GroceryBusiness();
		          
				System.out.println("Still Queries");
				System.out.println("Press 1 for YES ELSE Press 0");
				int p=sc.nextInt();
				if(p==1)
				{
					System.out.println("ASK YOUR QUESTIONS HERE");
					String s=sc.nextLine();
					sc.nextLine();
					System.out.println("GOT YOUR QUERY, OUR EXPERTS HELP YOU SOON");
					break;
				}
				else if(p==0)
				{
					System.out.println("THANK YOU!!! HOPE WE HELPED YOU");
					break;
				}
				break;
				default: 
				System.out.println("Invalid choice");
			
			}
			System.out.println("Do you want to continue in same (Business) Page.(true/false)");
			c=sc.nextBoolean();
			System.out.println();
		}
		
	}
	
	private void DairyBusiness()
	{
		try {
			
            int c;		
			BufferedReader file11=new BufferedReader(new FileReader("DairyBus.txt"));
			while((c=file11.read())!=-1)
			{
				System.out.print((char)c);
			}
			file11.close();
			
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	private void PotteryBusiness()
	{
try {
			
            int c;		
			BufferedReader file12=new BufferedReader(new FileReader("PotteryBus.txt"));
			while((c=file12.read())!=-1)
			{
				System.out.print((char)c);
			}
			file12.close();
			
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void GroceryBusiness()
	{
   try {
			
            int c;		
			BufferedReader file12=new BufferedReader(new FileReader("GroceryBus.txt"));
			while((c=file12.read())!=-1)
			{
				System.out.print((char)c);
			}
			file12.close();
			
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
			
		}
	}
	
	
	public void BusinessGuide()
	{
		System.out.println("*****THIS PAGE IS RELATED WITH BUSINESS GUIDE FOR FARMERS*****");
		System.out.println();
try {
			
            int c;		
			BufferedReader file13=new BufferedReader(new FileReader("BusinessGuide.txt"));
			while((c=file13.read())!=-1)
			{
				System.out.print((char)c);
			}
			file13.close();
			
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
			
		}	
	}    
}
public class Village_help
{
   
	public static void main(String args[]) throws IOException
	{	
		Scanner sc=new Scanner(System.in);
		int ch;
		boolean choice =true;
		Application a=new Application();
		try {
			boolean b=true;
		while(b)
		{
		System.out.println("********---->WELCOME TO VILLAGE HELP PORTAL----->******");
		System.out.println("1.Register in Village help Portal");
		System.out.println("2.Login (Already has an account)");
		System.out.println("");
		System.out.flush();
		System.out.println("Enter the choice");
	   String x=sc.next();
		switch(x)
		{
		    case "1":
		a.registration();
		b=false;
		break;
		case "2":
		a.login();
		b=false;
		break;
		default:
		System.out.println("please enter a valid choice");		
}}
		}
		catch(IOException e)
		{
			System.out.println("Exception caught"+e.getMessage());
		}
		
		while(choice)
		{
			System.out.println("1.Agriculture");
			System.out.println("2.Village development");
			System.out.println("3.Sanitation");
			System.out.println("4.Health");
			System.out.println("5.Education");
			System.out.println("Business Supports Are Also Available");
			System.out.println("6.BUSINESS RELATED INDUSTRY HELP");
			System.out.println("7.BUSINESS GUIDE");
			System.out.println("8.Exit");
			System.out.println("");

			System.out.println("");
			System.out.println("Enter the choice");
			ch=sc.nextInt();
			switch(ch)
			{
			case 1:	
			    a.Agriculture();		
				break;
			case 2:
				a.VillageDevelop();
				break;
			case 3:
				a.Sanitation();
				break;
			case 4:
				a.Health();
				break;
			case 5:
				a.Education();
				break;
			case 6:
				a.CityHelp();
				break;
			case 7:
				a.BusinessGuide();
				break;
			case 8:
				 System.out.println(">>>>>>>>THANK YOU TO VISIT THE SITE<<<<<<<<<");
				 System.exit(1);
			default:
					System.out.println("INVALID OPTION!!!!");
			}
			System.out.println("Do you want to continue(true/false)");
			 choice=sc.nextBoolean();
			
		}
	}
}

