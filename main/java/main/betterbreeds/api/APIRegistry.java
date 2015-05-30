package main.betterbreeds.api;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.ImmutableList;

/**
 * A helper class that will allow 
 * you to handle some of the things
 * that the animal will perform.
 * <p>Just a simple class. Sadly
 * you cannot remove any previous
 * handlers that have already been 
 * registered to the list.
 * 
 * @author Fir3will
 */
public final class APIRegistry
{
	/** A list of the drop handlers */
	private final List<AnimalDropHandler> dropHandlers;
	/** A list of the death handlers */
	private final List<AnimalDeathHandler> deathHandlers;
	/** A list of the init handlers */
	private final List<AnimalInitHandler> initHandlers;
	/** A list of the pregnancy handlers */
	private final List<AnimalPregnancyHandler> pregnancyHandlers;
	/** A list of the gender handlers */
	private final List<AnimalGenderHandler> genderHandlers;
	/** A list of the type handlers */
	private final List<AnimalTypeHandler> typeHandlers;
	/** A list of the update handlers */
	private final List<AnimalUpdateHandler> updateHandlers;

	/**
	 * Constructs a new registry 
	 * that will be registered to the mod.
	 * Used internally in the mod.
	 * <p><b><i>MOD MAKER, DO NOT CALL THIS CONSTRUCTOR!</p></b></i>
	 * <p><b><i>IT WILL BE REALLY USELESS FOR YOU TO CALL!</p></b></i>
	 */
	public APIRegistry()
	{
		dropHandlers = new ArrayList<AnimalDropHandler>();
		deathHandlers = new ArrayList<AnimalDeathHandler>();
		initHandlers = new ArrayList<AnimalInitHandler>();
		pregnancyHandlers = new ArrayList<AnimalPregnancyHandler>();
		genderHandlers = new ArrayList<AnimalGenderHandler>();
		typeHandlers = new ArrayList<AnimalTypeHandler>();
		updateHandlers = new ArrayList<AnimalUpdateHandler>();
	}

	/**
	 * Register a drop handler to the mod.
	 * These handlers will get called 
	 * everytime the animal drops something.
	 * <p>You can register to the list of
	 * items being dropped already.</p>
	 * 
	 * @param handler The handler class you want to register
	 */
	public void registerDropHandler(AnimalDropHandler handler)
	{
		dropHandlers.add(handler);
	}

	/**
	 * Register a death handler to the mod.
	 * These handlers will get called 
	 * everytime the animal dies from something.
	 * <p>You can make something happen when the 
	 * animal dies.</p>
	 * 
	 * @param handler The handler class you want to register
	 */
	public void registerDeathHandler(AnimalDeathHandler handler)
	{
		deathHandlers.add(handler);
	}

	/**
	 * Register an init handler to the mod.
	 * These handlers will get called 
	 * everytime the animal's entityInit()
	 * method is called on entity construction.
	 * <p>You can register a DataWatcher object
	 * or do anything else that's allowed.</p>
	 * 
	 * @param handler The handler class you want to register
	 */
	public void registerInitHandler(AnimalInitHandler handler)
	{
		initHandlers.add(handler);
	}

	/**
	 * Register a pregnancy handler to the mod.
	 * These handlers will get called 
	 * everytime anything that has to do
	 * with pregnancy happens to the animal.
	 * This includes:
	 * <li> Whether the animal can mate with another
	 * <li> While the animal is mating with another
	 * <li> Every tick the animal is pregnant
	 * <li> When the animal's pregnancy is done
	 * <p>You can make something happen every time
	 * the animal has to do with pregnancy.</p>
	 * 
	 * @param handler The handler class you want to register
	 */
	public void registerPregnancyHandler(AnimalPregnancyHandler handler)
	{
		pregnancyHandlers.add(handler);
	}

	/**
	 * Register a gender handler to the mod.
	 * These handlers will get called 
	 * everytime the animal's gender
	 * is set to something.
	 * <p>You can change the gender
	 * to something else.</p>
	 * 
	 * @param handler The handler class you want to register
	 */
	public void registerGenderHandler(AnimalGenderHandler handler)
	{
		genderHandlers.add(handler);
	}

	/**
	 * Register a type handler to the mod.
	 * These handlers will get called 
	 * everytime the animal's type
	 * is set to something.
	 * <p>You can change the type
	 * to something else.</p>
	 * 
	 * @param handler The handler class you want to register
	 */
	public void registerTypeHandler(AnimalTypeHandler handler)
	{
		typeHandlers.add(handler);
	}

	/**
	 * Register an update handler to the mod.
	 * These handlers will get called 
	 * everytime the animal is updated.
	 * <p>You can have something
	 * happen to the animal every update.</p>
	 * 
	 * @param handler The handler class you want to register
	 */
	public void registerUpdateHandler(AnimalUpdateHandler handler)
	{
		updateHandlers.add(handler);
	}

	/**
	 * The {@link AnimalHandlerAdapter} class is
	 * a helper class that implements every handler
	 * available, This is so you can easily override
	 * any method that's available in a handler.
	 * <p><b>WARNING: THIS CAN BE CPU INTENSIVE SO
	 * USE ONLY WHEN MULTIPLE OR ALL HANDLERS ARE NEEDED</b></p>
	 * 
	 * <p>If you need multiple handlers but don't
	 * want to be CPU intensive, you can have something like so:</p>
	 * <pre>
	 * Bar bar = new Bar();
	 * registerDropHandler(bar);
	 * registerTypeHandler(bar);
	 * registerInitHandler(bar);
	 * </pre>
	 * This will work just as well
	 * 
	 * @param handler The handler class you want to register
	 */
	public void registerAnimalAdapter(AnimalHandlerAdapter adapter)
	{
		registerDropHandler(adapter);
		registerDeathHandler(adapter);
		registerInitHandler(adapter);
		registerPregnancyHandler(adapter);
		registerGenderHandler(adapter);
		registerTypeHandler(adapter);
		registerUpdateHandler(adapter);
	}

	/**
	 * This method is used internally
	 * by the mod to add them all to
	 * the internal registry to be called
	 * <p>Really useless</p>
	 * 
	 * @return An Immutable list of all the Drop Handlers
	 */
	public final List<AnimalDropHandler> getDropHandlers()
	{
		return ImmutableList.copyOf(dropHandlers);
	}

	/**
	 * This method is used internally
	 * by the mod to add them all to
	 * the internal registry to be called
	 * <p>Really useless</p>
	 * 
	 * @return An Immutable list of all the Death Handlers
	 */
	public final List<AnimalDeathHandler> getDeathHandlers()
	{
		return ImmutableList.copyOf(deathHandlers);
	}

	/**
	 * This method is used internally
	 * by the mod to add them all to
	 * the internal registry to be called
	 * <p>Really useless</p>
	 * 
	 * @return An Immutable list of all the Init Handlers
	 */
	public final List<AnimalInitHandler> getInitHandlers()
	{
		return ImmutableList.copyOf(initHandlers);
	}

	/**
	 * This method is used internally
	 * by the mod to add them all to
	 * the internal registry to be called
	 * <p>Really useless</p>
	 * 
	 * @return An Immutable list of all the Pregnancy Handlers
	 */
	public final List<AnimalPregnancyHandler> getPregnancyHandlers()
	{
		return ImmutableList.copyOf(pregnancyHandlers);
	}

	/**
	 * This method is used internally
	 * by the mod to add them all to
	 * the internal registry to be called
	 * <p>Really useless</p>
	 * 
	 * @return An Immutable list of all the Gender Handlers
	 */
	public final List<AnimalGenderHandler> getGenderHandlers()
	{
		return ImmutableList.copyOf(genderHandlers);
	}

	/**
	 * This method is used internally
	 * by the mod to add them all to
	 * the internal registry to be called
	 * <p>Really useless</p>
	 * 
	 * @return An Immutable list of all the Type Handlers
	 */
	public final List<AnimalTypeHandler> getTypeHandlers()
	{
		return ImmutableList.copyOf(typeHandlers);
	}

	/**
	 * This method is used internally
	 * by the mod to add them all to
	 * the internal registry to be called
	 * <p>Really useless</p>
	 * 
	 * @return An Immutable list of all the Update Handlers
	 */
	public final List<AnimalUpdateHandler> getUpdateHandlers()
	{
		return ImmutableList.copyOf(updateHandlers);
	}
}
