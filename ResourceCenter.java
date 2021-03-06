import java.util.ArrayList;
public class ResourceCenter {

	public static void main(String[] args) {
		ArrayList<Camcorder> camcorderList = new ArrayList<Camcorder>();
	    ArrayList<Chromebook> chromebookList = new ArrayList<Chromebook>();
	    
	    camcorderList.add(new Camcorder("CC001", "Sony HDR-CX405", 35));
	    camcorderList.add(new Camcorder("CC002", "Panasonic HC-MDH2", 10));
	    chromebookList.add(new Chromebook("CB001", "ASUS Chromebook ", "Win 10"));
	    chromebookList.add(new Chromebook("CB002", "HP Chromebook", "Win 10"));
	    
	    int option = 0;
	    
	    while(option != 5) {
	    	ResourceCenter.menu();
	    	option = Helper.readInt("Enter your option > ");
	    	
	    	if (option == 1) {
	    		ResourceCenter.retrieveAllCamcorder(camcorderList);
	            ResourceCenter.viewAllChromebook(chromebookList);
	    	}
	    }
		
		}
	
	public static void menu() {
		ResourceCenter.setHeader("RESOURCE CENTRE APP");
	    System.out.println("1. Display Inventory");
	    System.out.println("2. Add item");
	    System.out.println("3. Loan item");
	    System.out.println("4. Return item");
	    System.out.println("5. Quit");
	    Helper.line(80, "-");
	}
	
	public static void setHeader(String header) {
		Helper.line(40, "=");
		System.out.println("Header :");
		Helper.line(40, "=");
	}
	
	public static String showAvailability (boolean available) {
		String avail;
		
		if (available == true) {
			avail = "Yes";
		}else {
			avail = "No";
		}
		return avail;
	}
	
	public static String retrieveAllCamcorder(ArrayList<Camcorder> camcorderList)  {
		String output = "";
		
		ResourceCenter.setHeader("CAMCORDER LIST");
		output = String.format("%-10s %-30s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION", "AVAILABLE", "DUE DATE","OPTICAL ZOOM");
		for (int i = 0; i < camcorderList.size(); i++) {
			output += String.format("%-10s %-30s %-10s %-10s %-20d\n", camcorderList.get(i).getAssetTag(), camcorderList.get(i).getDescription(), 
					ResourceCenter.showAvailability(camcorderList.get(i).getIsAvailable()), camcorderList.get(i).getDueDate(),camcorderList.get(i).getOpticalZoom());
    	    }
		return output;
	}
	
	public static void viewAllCamcorder(ArrayList<Camcorder> camcorderList) {
		System.out.println(retrieveAllCamcorder(camcorderList));
		
	}
	
	public static String retrieveAllChromebook(ArrayList<Chromebook> chromebookList) {
		String output = "";
		
		ResourceCenter.setHeader("CAMCORDER LIST");
		output = String.format("%-10s %-30s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION", "AVAILABLE", "DUE DATE","0S");
		for (int i = 0; i < chromebookList.size(); i++) {
			output += String.format("%-10s %-30s %-10s %-10s %-20d\n", chromebookList.get(i).getAssetTag(), chromebookList.get(i).getDescription(), 
					ResourceCenter.showAvailability(chromebookList.get(i).getIsAvailable()), chromebookList.get(i).getDueDate(),chromebookList.get(i).getOs());
    	    }
		return output;
		
	}
	
	public static void viewAllChromebook(ArrayList<Chromebook> chromebookList) {
		System.out.println(retrieveAllChromebook(chromebookList));
	}
	
	// ------------------------------ Add -----------------------------------
	public static Camcorder inputCamcorder() {
		String tag = Helper.readString("Enter asset tag > ");
	    String description = Helper.readString("Enter description > ");
	    int zoom = Helper.readInt("Enter optical zoom > ");
	    
	    Camcorder cc= new Camcorder(tag, description, zoom);
	    return cc;
	}
	
	public static void addCamcorder(ArrayList<Camcorder> camcorderList, Camcorder cc) {
		camcorderList.add(cc);
		if (camcorderList.add(cc) == true) {
			System.out.println("Camcorder Added !");
		}else{
			System.out.println("Camcorder Not Added !");
		}
	}

	public static Chromebook inputChromebook() {
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		String os = Helper.readString("Enter os > ");
		   
		Chromebook cb= new Chromebook(tag, description, os);
		return cb;
	}
	
	public static void addChromebook(ArrayList<Chromebook> chromebookList, Chromebook cb) {
		chromebookList.add(cb);
		
		if (chromebookList.add(cb) == true) {
			System.out.println("Camcorder Added !");
		}else{
			System.out.println("Camcorder Not Added !");
		}
	}
	
	public static boolean doLoanCamcorder(ArrayList<Camcorder> camcorderList, String tag, String dueDate) {
	    
	    boolean isLoaned = false;
	    
	    for (int i = 0; i < camcorderList.size(); i++) {
	        
	        String assetTag = camcorderList.get(i).getAssetTag();
	        
	        if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())
	            && camcorderList.get(i).getIsAvailable() == true) {
	          
	          camcorderList.get(i).setIsAvailable(false);
	          camcorderList.get(i).setDueDate(dueDate);
	          
	          isLoaned = true;
	          
	        }
	      }
	      return isLoaned;
	    }
	    public static void loanCamcorder(ArrayList<Camcorder> camcorderList) {
	      ResourceCenter.viewAllCamcorder(camcorderList);
	      String tag = Helper.readString("Enter asset tag > ");
	      String due = Helper.readString("Enter due date > ");
	      Boolean isLoaned =doLoanCamcorder(camcorderList, tag, due);
	      if (isLoaned == false) {
	        System.out.println("Invalid asset tag");
	      } else {
	        System.out.println("Camcorder " + tag + " loaned out");
	      }
	    }
	    
	    public static boolean doLoanChromebook(ArrayList<Chromebook> chromebookList, String tag, String dueDate) {
	      // write your code here
	      boolean isLoaned = false;
	      for (int i = 0; i < chromebookList.size(); i++) {
	        String assetTag = chromebookList.get(i).getAssetTag();
	              if (tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag()) && chromebookList.get(i).getIsAvailable() == true) {
	                chromebookList.get(i).setIsAvailable(false);
	                  chromebookList.get(i).setDueDate(dueDate);
	                  
	                  isLoaned = true;
	              }
	      }
	      return true;
	    }
	    public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
	      // write your code here
	      ResourceCenter.viewAllChromebook(chromebookList);
	      String tag = Helper.readString("Enter asset tag > : ");
	          String due = Helper.readString("Enter due date > : ");
	          Boolean isLoaned = doLoanChromebook(chromebookList, tag, due);
	          if (isLoaned == false) {
	            System.out.println("Invalid asset tag");
	          }
	          else{
	            System.out.println("Chromebook " + tag + " loaned out");
	          }
	      
	    }
	    
	    //================================= Option 4 Return =================================
	    public static boolean doReturnCamcorder(ArrayList<Camcorder> camcorderList,String tag) {
	      boolean isReturned = false;

	      for (int i = 0; i < camcorderList.size(); i++) {
	        if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())
	            && camcorderList.get(i).getIsAvailable() == false) {
	          camcorderList.get(i).setIsAvailable(true);
	          camcorderList.get(i).setDueDate("");
	          isReturned = true;
	          
	        }
	      }
	      return isReturned;
	      
	    }
	    public static void returnCamcorder(ArrayList<Camcorder> camcorderList) {
	      RC.viewAllCamcorder(camcorderList);
	      String tag = Helper.readString("Enter asset tag > ");
	      Boolean isReturned = doReturnCamcorder(camcorderList, tag);
	      
	      if (isReturned == false) {
	        System.out.println("Invalid asset tag");
	      } else {
	        System.out.println("Camcorder " + tag + " returned");
	      }
	    }

	    public static boolean doReturnChromebook(ArrayList<Chromebook> chromebookList,String tag){
	      boolean isReturned = false;
	      // write your code here
	      for (int i = 0; i < chromebookList.size(); i++) {
	        if (tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag()) && chromebookList.get(i).getIsAvailable() == false) {
	          chromebookList.get(i).setIsAvailable(true);
	              chromebookList.get(i).setDueDate("");
	              isReturned = true;
	        }
	      }
	      return isReturned;
	    }
	    public static void returnChromebook(ArrayList<Chromebook> chromebookList) {
	      // write your code here
	      // write your code here
	      RC.viewAllChromebook(chromebookList);
	        String tag = Helper.readString("Enter asset tag > ");
	        boolean isReturned = doReturnChromebook(chromebookList, tag);
	        
	        if (isReturned == false) {
	          System.out.println("Invalid asset tag");
	        }
	        else {
	          System.out.println("Chromebook " + tag + " returned");
	        }
	    }


}


    
    	    