import java.util.concurrent.ThreadLocalRandom;

public class Egaliter {
	
	static int prisonerCount = 100;
	static int iterations = 1;
	static int day = prisonerCount;
	static boolean[][] checklist = new boolean[prisonerCount][prisonerCount];
	static int time = 0;
	static int today = 0;
	static boolean lightOn = false;

	public static void main(String[] args) throws InterruptedException {
		int sum = 0;
		for(int i = 1; i <= iterations; i++){
			time = 0;
			initializeChecklists();

			while(true){
				updateToday();
				time++;
				
				int randomPrisoner = ThreadLocalRandom.current().nextInt(0, prisonerCount);
				
				if(lightOn){
					checklist[randomPrisoner][previousDay(today)] = true;
					lightOn = false;
					if(checklistComplete(randomPrisoner)){
						sum += time;
						break;
					}
				}
				
				if(randomPrisoner == today){
					lightOn = true;
				}
			}

			clearChecklists();
		}
		
		System.out.println((sum / iterations));
	}
	
	public static boolean checklistComplete(int prisonerNum){
		for(int i = 0; i < checklist[prisonerNum].length; i++){
			if(checklist[prisonerNum][i] == false){
				return false;
			}
		}
		return true;
	}
	
	public static void updateToday(){
		today = (time % prisonerCount);
	}
	
	public static int previousDay(int day){
		return (prisonerCount + day - 1) % prisonerCount;
	}
	
	public static void initializeChecklists(){
		for(int i = 0; i < prisonerCount; i++){
			checklist[i][i] = true;
		}
	}
	
	public static void clearChecklists(){
		for(int i = 0; i < prisonerCount; i++){
			for(int j = 0; j < prisonerCount; j++){
				checklist[i][j] = false;
			}
		}
	}
}
