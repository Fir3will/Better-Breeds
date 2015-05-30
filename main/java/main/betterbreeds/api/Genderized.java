package main.betterbreeds.api;

public interface Genderized
{
	public boolean isFemale();

	public void setFemale(boolean female);

	public boolean isPregnant();

	public void setPregnant(boolean pregnant);

	public int getType();

	public void setType(int type);
}