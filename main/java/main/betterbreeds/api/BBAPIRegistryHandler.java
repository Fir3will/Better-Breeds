package main.betterbreeds.api;

/**
 * This class has to be an interface of what you want
 * to register handlers to the mod. You have to override
 * the method below and add any handlers to the APIRegistry
 * instance provided to you.
 */
public interface BBAPIRegistryHandler
{
	/**
	 * This method will allow you to register
	 * any handlers you want to the mod.
	 * You can even use the same instance of
	 * your class to register it to the registry.
	 * <p>Like so:</p>
	 * <pre>
	 * public class Foo implements BBAPIRegistryHandler, AnimalGenderHandler
	 * {
	 * 	public void handlerAPIRegistry(APIRegistry registry)
	 * 	{
	 * 		registry.registerGenderHandler(this);
	 * 	}
	 * 	public void setGender(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type)
	 * 	{
	 * 		Random r = new Random();
	 * 		if (!genderizedAnimal.isFemale() && r.nextBoolean())
	 * 		{
	 * 			genderizedAnimal.setFemale(true);
	 * 		}
	 * 	}
	 * }
	 * </pre>
	 * As you can see, you can just use that
	 * class itself to handle the action you want.
	 * 
	 * @param registry The registry you have to register your handlers to
	 */
	public void handlerAPIRegistry(APIRegistry registry);
}
