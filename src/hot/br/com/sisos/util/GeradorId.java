package br.com.sisos.util;



import java.net.InetAddress;
import java.net.UnknownHostException;

public class GeradorId extends Number
{
	/**
	 * The machine clock precision in milliseconds. This is
	 * the minimum amount of time that the machine can safely 
	 * count. Some machines cannot count in milliseconds, but
	 * rather in tenths or hundredths of seconds.
	 */

	private static int CLOCK_PRECISION = 1000;
	
	/**
	 * Unique identifier for the current JVM.
	 */

	private static int jvmUnique = (new Object()).hashCode();

	/**
	 * This host's IP address cache.
	 */

	private static int ipAddressCache = 0;

	/**
	 * Last time the lastCount attribute was reset to
	 * Integer.MIN_VALUE.
	 */

	private static long lastTime = System.currentTimeMillis();

	/**
	 * The last hash key used to create an UUID object.
	 */

	private static int lastCount = Integer.MIN_VALUE;

	/**
	 * The mutually exclusive lock, to prevent concurrency
	 * issues.
	 */

	private static Object mutex = new Object();

	/**
	 * The machine's IP address
	 */

	private int ipAddress;

	/**
	 * Unique integer that helps create the UUID.
	 */

	private int unique;

	/**
	 * Long used to record the time chunck when the UUID was created.
	 */

	private long time;

	/**
	 * Integer used to create a hash key for the UUID.
	 */

	private int count;

	/**
	 * Creates a pure identifier that is universally unique.
	 * 
	 * The construction of UUIDs is synchronized under the mutex
	 * monitor, in order to guarantee uniqueness. This means that
	 * the class can only create one UUID at a time.
	 */ 

	public GeradorId()
	{
		synchronized (getMutex())
		{
			if (getLastCount() == Integer.MAX_VALUE)
			{
				boolean done = false;
			
				while (!done)
				{
					setTime (System.currentTimeMillis());
			
					if (getTime() < getLastTime() + CLOCK_PRECISION)
					{
						// Pause to wait for time chunck to change

						try
						{
							Thread.currentThread().sleep(getLastTime() - getTime() + 1);
						}
						catch (java.lang.InterruptedException e)
						{
						}
						
						continue;
					}
					else
					{
						setLastTime (getTime());
						setLastCount (Integer.MIN_VALUE);
						
						done = true;
					}
				}
			}
			else
			{
				setTime (lastTime);
			}

			setUnique(getJvmUnique());

			setCount (getLastCount() + 1);
			setLastCount(getCount());
			
			setIpAddress(getIpAddressCache());
		}
	}

	/**
	 * Returns a hashcode for the UUID.
	 *
	 * @return the hashcode
	 */

	public int hashCode()
	{
		return intValue();
	}

	/**
	 * Compares two UUIDs for content equality.
	 *
	 * @param	anObject	the Object to compare with
	 * @return	true if these Objects are equal; false otherwise.
	 */

	public boolean equals (Object anObject)
	{
		if (anObject == null)
			return false;
			
		try
		{
			GeradorId other = (GeradorId) anObject;
			
			return getIpAddress() == other.getIpAddress() &&
				getUnique() == other.getUnique() &&
				getTime() == other.getTime() &&
				getCount() == other.getCount();
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}

	/**
	 * Returns the string representation of the UUID, the
	 * greatest degree of uniqueness (160 bits).
	 */

	public String toString()
	{
		return Integer.toString(getIpAddress(), 16) + ":" +
			Integer.toString(getUnique(), 16) + ":" +
			Long.toString(getTime(), 16)	+ ":" +
			Integer.toString(getCount(), 16);
	}

	/**
	 * Returns a long value of this UUID, a great degree
	 * of uniqueness (64 bits).
	 * 
	 * @see java.lang.Number#longValue()
	 */
	
	public long longValue ()
	{
		return getIpAddress() ^
			getUnique() ^
			getTime() ^
			getCount();
	}

	/**
	 * Returns an integer value of this UUID, a good degree
	 * of uniqueness (32 bits).
	 * 
	 * @see java.lang.Number#intValue()
	 */

	public int intValue ()
	{
		return getIpAddress() ^
			getUnique() ^ 
			((int) getTime()) ^
			((int) (getTime() >>> 32)) ^
			getCount();
	}

	/**
	 * Returns a double value of this UUID. This is the
	 * same value returned by the longValue() method.
	 * 
	 * @see longValue()
	 * @see java.lang.Number#doubleValue()
	 */

	public double doubleValue()
	{
		return longValue();
	}

	/**
	 * Returns a float value of this UUID. This is the
	 * same value returned by the intValue() method.
	 * 
	 * @see intValue()
	 * @see java.lang.Number#floatValue()
	 */

	public float floatValue()
	{
		return intValue();
	}

	/**
	 * Returns the ipAddressCache.
	 * @return int
	 */

	private static int getIpAddressCache()
	{
		if (ipAddressCache == 0)
		{
			int address = 0;
			
			try
			{
				byte [] addressArray = InetAddress.getLocalHost().getAddress();
			
				address = 
					(addressArray[0] << 24) +
					(addressArray[1] << 16) +
					(addressArray[2] << 8) +
					(addressArray[3]);
			}
			catch (UnknownHostException e)
			{
				// Unfortunatelly the host address could not be found.
				// We will have to live without it.
			}

			setIpAddressCache(address);
		}

		return ipAddressCache;
	}

	/**
	 * Returns the jvmUnique.
	 * @return int
	 */

	private static int getJvmUnique()
	{
		return jvmUnique;
	}

	/**
	 * Returns the lastCount.
	 * @return int
	 */

	private static int getLastCount()
	{
		return lastCount;
	}

	/**
	 * Returns the lastTime.
	 * @return long
	 */

	private static long getLastTime()
	{
		return lastTime;
	}

	/**
	 * Returns the mutex.
	 * @return Object
	 */

	private static Object getMutex()
	{
		return mutex;
	}

	/**
	 * Returns the count.
	 * @return int
	 */

	private int getCount()
	{
		return count;
	}

	/**
	 * Returns the ipAddress.
	 * @return int
	 */

	private int getIpAddress()
	{
		return ipAddress;
	}

	/**
	 * Returns the time.
	 * @return long
	 */

	private long getTime()
	{
		return time;
	}

	/**
	 * Returns the unique.
	 * @return int
	 */

	private int getUnique()
	{
		return unique;
	}

	/**
	 * Sets the ipAddressCache.
	 * @param ipAddressCache The ipAddressCache to set
	 */

	private static void setIpAddressCache (int ipAddressCache)
	{
		GeradorId.ipAddressCache = ipAddressCache;
	}

	/**
	 * Sets the lastCount.
	 * @param lastCount The lastCount to set
	 */

	private static void setLastCount(int lastCount)
	{
		GeradorId.lastCount = lastCount;
	}

	/**
	 * Sets the lastTime.
	 * @param lastTime The lastTime to set
	 */

	private static void setLastTime (long lastTime)
	{
		GeradorId.lastTime = lastTime;
	}

	/**
	 * Sets the count.
	 * @param count The count to set
	 */

	private void setCount(int count)
	{
		this.count = count;
	}

	/**
	 * Sets the ipAddress.
	 * @param ipAddress The ipAddress to set
	 */

	private void setIpAddress(int ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	/**
	 * Sets the time.
	 * @param time The time to set
	 */

	private void setTime(long time)
	{
		this.time = time;
	}

	/**
	 * Sets the unique.
	 * @param unique The unique to set
	 */
	
	private void setUnique(int unique)
	{
		this.unique = unique;
	}
}
