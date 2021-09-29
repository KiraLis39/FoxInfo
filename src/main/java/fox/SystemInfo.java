package fox;

import java.awt.Toolkit;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Properties;

import com.sun.management.OperatingSystemMXBean;


public class SystemInfo {	
	private final static OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	private static StringBuilder sb;
	
	private SystemInfo() {}
	
	public static String getAllSystemParameters() {
		sb = new StringBuilder();
		Properties mass = System.getProperties();
		ArrayList<?> lstK = new ArrayList<Object>(mass.keySet());
		ArrayList<?> lstV = new ArrayList<Object>(mass.values());

		int i = 0;
		while (lstK.size() > i) {
			sb.append(lstK.get(i) + ": " + lstV.get(i));
			i++;
		}
		
		return sb.toString();
	}

	public static void printAll() {
		System.out.println("vendor: " + System.getProperty("java.vm.vendor"));
		System.out.println("vendor.url: " + System.getProperty("java.vendor.url"));
		System.out.println();

		System.out.println("\t*** СВОЙСТВА ПК ***");
		System.out.println();

		System.out.println("\t*** USER ***");
		System.out.println("Имя пользователя:\t" + System.getProperty("user.name"));
		System.out.println("Страна и язык: \t" + System.getProperty("user.country") + "_" + System.getProperty("user.language"));
		System.out.println("Кодировка системы: " + System.getProperty("file.encoding") + " ("	+ System.getProperty("sun.jnu.encoding") + ")");
		System.out.println("Временная зона:\t"	+ (System.getProperty("user.timezone") == null ? System.getProperty("user.timezone") : "none"));
		System.out.println("Домашний каталог:\t" + System.getProperty("user.home"));
		System.out.println("Текущий каталог:\t" + System.getProperty("user.dir"));
		System.out.println();

		System.out.println("\t*** OS ***");
		System.out.println("Имя данной ОС:\t" + System.getProperty("os.name"));
		System.out.println("Версия данной ОС:\t" + System.getProperty("os.version"));
		System.out.println("Архитектура ОС:\t" + System.getProperty("os.arch"));
		System.out.println("Семейство ОС:\t" + System.getenv().get("OS"));
		System.out.println();

		System.out.println("\t*** PC ***");
		System.out.println("Имя компьютера:\t" + System.getenv().get("COMPUTERNAME"));
		System.out.println("Имя процессора:\t" + System.getProperty("sun.cpu.isalist").toUpperCase());
		System.out.println("Архитектура процессора:\t" + System.getProperty("sun.arch.data.model") + "bit");
		System.out.println("Ядер процессора:\t" + Runtime.getRuntime().availableProcessors());
		System.out.println("О процессоре:\t" + System.getenv().get("PROCESSOR_IDENTIFIER"));		
		System.out.println();

		System.out.println("\t*** JVM ***");
		System.out.println("Текущая Java-машина:\t" + System.getProperty("java.vm.name") + "\n\t\t" + System.getProperty("java.specification.name") + "\n\t\t" + System.getProperty("java.runtime.name"));
		System.out.println("Версия Java-машины:\t" + System.getProperty("java.vm.version") + " (" + System.getProperty("java.runtime.version") + " )");
		System.out.println("Каталог Java-машины:\t" + System.getProperty("java.home"));
		System.out.println("Архетиктура Java:\t" + System.getProperty("sun.arch.data.model") + "bit");
		System.out.println();

		System.out.println("\t*** NET ***");
		System.out.println("http.proxyHost    =\t" + System.getProperty("http.proxyHost"));
		System.out.println("https.proxyHost  =\t" + System.getProperty("https.proxyHost"));
		System.out.println("ftp.proxyHost      =\t" + System.getProperty("ftp.proxyHost"));
		System.out.println("socksProxyHost  =\t" + System.getProperty("socksProxyHost"));
		System.out.println();

		System.out.println("\t*** SCREEN ***");
		System.out.println("Разрешение экрана в dpi: " + Toolkit.getDefaultToolkit().getScreenResolution());
		System.out.println("Разрешение экрана в px: " + (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() + " х " + (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		System.out.println();

		System.out.println("\t*** HDD ***");
		File[] roots = File.listRoots();
		for (File root : roots) {
			System.out.println("Диск: " + root.getAbsolutePath());
			System.out.println("Всего места: " + (root.getTotalSpace() / 1048576 / 1024) + " Гб. или " + (root.getTotalSpace() / 1048576) + " мб.");
			System.out.println("Свободно: " + (root.getFreeSpace() / 1048576 / 1024) + " Гб. или " + (root.getFreeSpace() / 1048576) + " мб.");
			System.out.println("Занято: " + ((root.getTotalSpace() / 1048576 / 1024) - (root.getFreeSpace() / 1048576 / 1024)) + " Гб. " + "или " + ((root.getTotalSpace() / 1048576) - (root.getFreeSpace() / 1048576)) + " мб.");
			System.out.println();
		}

		System.out.println("\t*** MEMORY ***");
		System.out.println("Данная программа использует "	+ 
				(MEMORY.getUsedJvmMemory() / 1048576L) + "мб из " +
				(MEMORY.getTotalJvmMemory() / 1048576L) + "мб выделенных под неё.");

		System.out.println("Max memory:\t" + MEMORY.getMaxJvmMemory() + " byte (" + (MEMORY.getMaxJvmMemory() / 1048576L) + "mb)");
		System.out.println("Used memory:\t" + MEMORY.getUsedJvmMemory() + " byte (" + (MEMORY.getUsedJvmMemory() / 1048576L) + "mb)");
		System.out.println("Free memory:\t" + MEMORY.getFreeJvmMemory() + " byte (" + (MEMORY.getFreeJvmMemory() / 1048576L) + "mb)");

	    System.out.println("\nДополнительно:\n"
	    		+ "Всего ОЗУ > " + (os.getTotalMemorySize() / 1048576L) + "мб,\n"
	    		+ "Свободно ОЗУ > " + (os.getFreeMemorySize() / 1048576L) + "мб,\n"
	    		+ "Free Swap Size > " + (os.getFreeSwapSpaceSize() / 1048576L) + "мб,\n"
	    		+ "Commited Virtual Memory > " + (os.getCommittedVirtualMemorySize() / 1048576L) + "мб.\n");
	   
	    System.out.println("\t*** FINAL ***");
	}

	public static class MEMORY {
		public static long getTotalJvmMemory() {return Runtime.getRuntime().totalMemory();}
		public static long getMaxJvmMemory() {return Runtime.getRuntime().maxMemory();}
		public static long getUsedJvmMemory() {return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();}
		public static long getFreeJvmMemory() {return Runtime.getRuntime().freeMemory();} // (Memory / 1048576L) = "мб"
		
		public static long getTotalSystemMemory() {return os.getTotalMemorySize();}
		public static long getFreeSystemMemory() {return os.getFreeMemorySize();}
		public static long getTotalSwapMemory() {return os.getTotalSwapSpaceSize();}
		public static long getFreeSwapMemory() {return os.getFreeSwapSpaceSize();}
		public static long getCommittedVirtualMemory() {return os.getCommittedVirtualMemorySize();} // (Memory / 1048576L) = "мб"
	}
	
	public static class USER {
		public static String getUSER_LANG() {
			return System.getProperty("user.country") + "_" + System.getProperty("user.language");
		}

		public static String getUSER_HOME() {
			return System.getProperty("user.home");
		}

		public static String getUSER_NAME() {
			return System.getProperty("user.name");
		}

		public static String getUSER_DIR() {
			return System.getProperty("user.dir");
		}
	}

	public static class OS {
		public static String getOS_NAME() {
			return System.getProperty("os.name");
		}

		public static String getOS_ARCH() {
			return System.getProperty("os.arch");
		}

		public static String getOS_VERSION() {
			return System.getProperty("os.version");
		}

		public static String getOS_ENCODE() {
			return System.getProperty("file.encoding") + "_" + System.getProperty("sun.jnu.encoding");
		}

		public static String getOS_TIMEZONE() {
			return System.getProperty("user.timezone");
		}

		public static String getOS_TYPE() {
			return System.getenv().get("OS");
		}
	}

	public static class JAVA {
		public static String VM_NAME() {
			return System.getProperty("java.vm.name") + "\n" + System.getProperty("java.specification.name") + "\n"
					+ System.getProperty("java.runtime.name");
		}

		public static String JAVA_VERSION() {
			return System.getProperty("java.version") + "\n" + System.getProperty("java.runtime.version");
		}

		public static String JAVA_HOME() {
			return System.getProperty("java.home");
		}

		public static String JAVA_ARCH() {
			return System.getProperty("sun.arch.data.model") + "bit";
		}
	}

	public static class CPU {
		public static String getCPU_ARCH() {
			return System.getProperty("sun.arch.data.model") + "bit";
		}

		public static String getCPU_MODEL() {
			return System.getProperty("sun.cpu.isalist").toUpperCase();
		}

		public static String getCPU_NAME() {
			return System.getenv().get("COMPUTERNAME");
		}

		public static int getCPU_CORES() {
			return Runtime.getRuntime().availableProcessors();
		}

		public static String getCPU_IDENTIFIER() {
			return System.getenv().get("PROCESSOR_IDENTIFIER");
		}
	}

	public static class NET {
		public static String NET_HTTP_HOST() {
			return System.getProperty("http.proxyHost");
		}

		public static String NET_HTTPS_HOST() {
			return System.getProperty("https.proxyHost");
		}

		public static String NET_FTP_HOST() {
			return System.getProperty("ftp.proxyHost");
		}

		public static String NET_SOCKS_HOST() {
			return System.getProperty("socksProxyHost");
		}
	}

	public static class SCREEN {
		public static int SCREEN_SIZE_DPI() {
			return Toolkit.getDefaultToolkit().getScreenResolution();
		}

		public static String SCREEN_SIZE() {
			return Toolkit.getDefaultToolkit().getScreenSize().getWidth() + " х "
					+ Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		}
	}
}