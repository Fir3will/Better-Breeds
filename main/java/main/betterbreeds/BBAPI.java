package main.betterbreeds;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import main.betterbreeds.api.APIRegistry;
import main.betterbreeds.api.AnimalDeathHandler;
import main.betterbreeds.api.AnimalDropHandler;
import main.betterbreeds.api.AnimalGenderHandler;
import main.betterbreeds.api.AnimalInitHandler;
import main.betterbreeds.api.AnimalPregnancyHandler;
import main.betterbreeds.api.AnimalTypeHandler;
import main.betterbreeds.api.AnimalUpdateHandler;
import main.betterbreeds.api.RegistryPriority;
import main.com.hk.bb.util.JavaHelp;

public class BBAPI
{
	private static final List<AnimalDropHandler> dropHandlers = JavaHelp.newArrayList();
	private static final List<AnimalDeathHandler> deathHandlers = JavaHelp.newArrayList();
	private static final List<AnimalInitHandler> initHandlers = JavaHelp.newArrayList();
	private static final List<AnimalPregnancyHandler> pregnancyHandlers = JavaHelp.newArrayList();
	private static final List<AnimalGenderHandler> genderHandlers = JavaHelp.newArrayList();
	private static final List<AnimalTypeHandler> typeHandlers = JavaHelp.newArrayList();
	private static final List<AnimalUpdateHandler> updateHandlers = JavaHelp.newArrayList();

	public static void addLists(APIRegistry registry)
	{
		dropHandlers.addAll(registry.getDropHandlers());
		deathHandlers.addAll(registry.getDeathHandlers());
		initHandlers.addAll(registry.getInitHandlers());
		pregnancyHandlers.addAll(registry.getPregnancyHandlers());
		genderHandlers.addAll(registry.getGenderHandlers());
		typeHandlers.addAll(registry.getTypeHandlers());
		updateHandlers.addAll(registry.getUpdateHandlers());
		Collections.sort(dropHandlers, priorityComparator);
		Collections.sort(deathHandlers, priorityComparator);
		Collections.sort(initHandlers, priorityComparator);
		Collections.sort(pregnancyHandlers, priorityComparator);
		Collections.sort(genderHandlers, priorityComparator);
		Collections.sort(typeHandlers, priorityComparator);
		Collections.sort(updateHandlers, priorityComparator);
	}

	private static final Comparator<Object> priorityComparator = new Comparator<Object>()
	{
		@Override
		public int compare(Object o1, Object o2)
		{
			int a = 50;
			int b = 50;
			if (o1.getClass().isAnnotationPresent(RegistryPriority.class))
			{
				a = o1.getClass().getAnnotation(RegistryPriority.class).priority();
			}
			if (o2.getClass().isAnnotationPresent(RegistryPriority.class))
			{
				b = o2.getClass().getAnnotation(RegistryPriority.class).priority();
			}
			return Integer.compare(a, b);
		}
	};
}
