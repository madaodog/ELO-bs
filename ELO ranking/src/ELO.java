import java.util.Arrays;
import java.util.List;

public class ELO {
	private final String[] teams;
	private int[] eloRating;
	private int kValue;
	
	public ELO(String[] teams, int[] eloRating, int kValue) {
		this.teams = teams;
		this.eloRating = eloRating;
		this.kValue = kValue;
	}
	
	public int[] getEloRating() {
		return eloRating.clone();
	}
	
	public String[] getTeams() {
		return teams;
	}
	
	public int getKValue() {
		return kValue;
	}
	
	public void append_results(String[] results) {
		for(int i = 0; i < results.length; i++) {
			for(int j = 0; j < teams.length; j++) {
				if(results[i].contains(teams[j] + "3") || results[i].contains("3" + teams[j])) {
					for(int k = j; k < teams.length; k++) {
						if(results[i].contains(teams[j]) && j != k) {
							adjustELO(getTeams()[j], getEloRating()[j], expectancyBetweenTwo(getEloRating()[j], getEloRating()[k]), 1);
							adjustELO(getTeams()[k], getEloRating()[k], expectancyBetweenTwo(getEloRating()[k], getEloRating()[j]), 0);
						}
					}
				}
				if(results[i].contains(teams[j] + "0") || results[i].contains("0" + teams[j]) || results[i].contains(teams[j] + "1") || results[i].contains("1" + teams[j]) || results[i].contains(teams[j] + "2") || results[i].contains("2" + teams[j])) {
					for(int k = j; k < teams.length; k++) {
						if(results[i].contains(teams[j]) && j != k) {
							adjustELO(getTeams()[j], getEloRating()[j], expectancyBetweenTwo(getEloRating()[j], getEloRating()[k]), 0);
							adjustELO(getTeams()[k], getEloRating()[k], expectancyBetweenTwo(getEloRating()[k], getEloRating()[j]), 1);
				}
			}
		
	}
			}
		}
	}
	
	public int expectancyBetweenTwo(int eloRating1, int eloRating2) {
		int eloDifference = eloRating1 - eloRating2;
		double expectancyValue = 1 / 1 + Math.pow(10, eloDifference/400);
		int intExpect = (int)expectancyValue;
		return intExpect;	
	}
	
	public void adjustELO(String team, int eloRating, int expectancy, int result) {
		int teamIndex = 0;
		for(int i = 0; i < teams.length; i++) {
			if(teams[i] == team) {
				teamIndex = i;
			}	
		}
		getEloRating()[teamIndex] = getEloRating()[teamIndex] + getKValue() * (result - expectancy);
	}

}
