package main.betterbreeds.api;

/**
 * Implement this interface if you want to
 * add genders, types and pregnancy to your
 * mob. This is implemented by every BB animal.
 * 
 * @author Fir3will
 */
public interface Genderized
{
	/**
	 * This is the gender of the animal.
	 * 
	 * @return 
	 * true if it's a female.
	 * <p> 
	 * false if it's a male.
	 */
	public boolean isFemale();

	/**
	 * This is to set the gender of the animal.
	 * 
	 * @param female set it to true if you want the animal to be a female
	 */
	public void setFemale(boolean female);

	/**
	 * This is whether the animal is pregnant.
	 * 
	 * @return 
	 * true if it is pregnant.
	 * <p> 
	 * false if it is not pregnant.
	 */
	public boolean isPregnant();

	/**
	 * This is to set the animal pregnant or not.
	 * 
	 * @param pregnant set it to true if you want the animal to be pregnant
	 */
	public void setPregnant(boolean pregnant);

	/**
	 * This is the type of the animal.
	 * Not {@link AnimalType}, but the
	 * like the texture of the animal.
	 * 
	 * @return normally an integer from 0-9 to return the type of texture of the animal.
	 */
	public int getType();

	/**
	 * This is to set the animal's type.
	 * This will almost automatically change
	 * the texture too.
	 * 
	 * @param type This must be in the range of the amount of textures the animal has!
	 * If it's not it will most likely crash!
	 */
	public void setType(int type);
}