


import java.util.*;

public class FoodCafe {

	static Scanner sc = new Scanner(System.in); 	//scanner input
	static ListQueue productionLine = new ListQueue();// productionLine
	static int foodtime=1; //counting time
	static int customerOrder=0;//counting customer
	static int orderQuantity = 0;//counting order

	public static void main(String args[]){
		boolean CookTimeCheck = false;
		System.out.println("------------ SETUP SIMULATOIN ENVIRONMENT ------------");
		System.out.print( "Input simulation length (min):" );
		int CookTime = sc.nextInt();
		if(CookTime < 1){ //checking input is bigger than one
			CookTimeCheck = true;
		}
		while(CookTimeCheck){ //checking input is bigger than one, if not input again
			System.out.println("please write down the correct number");
			System.out.print( "Input simulation length (min):" );
			CookTime = sc.nextInt();
			if(CookTime>1){
				CookTimeCheck = false;
			}
		}
		System.out.println("------------------ START SIMULATOIN ------------------");
		
		while (foodtime<=CookTime){ //loop order
			boolean time = false;
			System.out.print( "Order item [1-coffee,2-Soft Drink, 3-Hot Dog, 4-French fries, 0-Finsih]:" );
				try{ //checking Exception
				int item= sc.nextInt();	

				if(item>=1 && item<=4){ //if input is 1-4 add item to the queue
				customerOrder++;
				FoodItem Order = addItem(item);
					if(Order.getRemainCookTime()>0) {
						productionLine.enqueue(Order); 
					}
				}
				if(item==0);{ //if input is 0 ,After minute +1
					time=true;
				}
				if(item<0||item>4){ //if input over 1-4, output error message
					throw new InvalidRangeInputException(); }
					
				while(item != 0 ){	//do the next order
					System.out.print( "Order item [1-coffee,2-Soft Drink, 3-Hot Dog, 4-French fries, 0-Finsih]:" );
					item= sc.nextInt();

				if(item>=1 && item<=4){ //if input is 1-4 add item to the queue
				FoodItem Order = addItem(item);
					if(Order.getRemainCookTime()>0) {
						productionLine.enqueue(Order); 
					}
				}
					if(item<0||item>4){ //if input over 1-4, output error message
						throw new InvalidRangeInputException(); 
					}
					if(item==0);{ //if input is 0 ,After minute +1
						time=true;
					}
				}

		Divider(1); //output Divider "----"
		System.out.println("After " + foodtime + " minute(s):");
		CookTime(); // Front queue item -1 time 
		if(time){ //if input is 0 ,After minute +1
			foodtime ++;
		}
		System.out.println("Cooking queue# " + productionLine);
		Divider(2);	//output Divider "===="
		
		}catch(InvalidRangeInputException e){ //error message for over 1-4
			System.out.println("Invalid choice!");
			sc.nextLine();
			continue;
		}catch(InputMismatchException e){ //error message for a-z, A-Z
			System.out.println("Wrong input!");
			sc.nextLine();
			continue;
		}
	}

		System.out.println();
			System.out.println("------------------ END OF SIMULATION -----------------");
			System.out.println("Total minute simulated: " + CookTime + " minutes");
			System.out.println("Number of customer served: " + customerOrder + " customer(s)");
			System.out.println("Item ordered during the simulation: " + orderQuantity);
			System.out.println("Outstanding item at the end of simulation: " + productionLine.count());
			Divider(1);//output Divider "----"


}
	public static FoodItem addItem(int item){  //if input is 1-4 add item to the queue
		orderQuantity++;
		if(item == 1){
			return new FoodItem("Coffee", 1);}
		else if(item == 2){
			return new FoodItem("Soft drink", 0);}
		else if(item == 3){
			return new FoodItem("Hot dog", 2);}
		else{
			return new FoodItem("French fries", 3);}
}
	public static void CookTime(){ 	 // Front queue item -1 time 
		if(!productionLine.empty()){ 
			FoodItem Frontqueue = ((FoodItem)productionLine.front()); // coll Front queue item to -1
			Frontqueue.reduceCookTime(); 

			if(Frontqueue.getRemainCookTime()<=0){ //if Front queue item =0, delete
				productionLine.dequeue(); 
			}
		}
		}
	public static void Divider(int Divider){ 	
		if(Divider==1){ //loop a Divider "-------"
			for(int i=0;i<35;i++){
				System.out.print("-");}
			System.out.println();
			}
		else if(Divider==2){ //loop a Divider "===="
			for(int i=0;i<35;i++){
				System.out.print("=");}
			System.out.println();
		}

}
}