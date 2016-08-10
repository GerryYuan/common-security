package link.gerry.common.redis;

public class RedisHelper {

	static final String REDIS_PERMISSION_KEY = "_permission_";

	public static String getPermissionKey() {
		return REDIS_PERMISSION_KEY;
	}

	public static String getPermissionKey(Integer id) {
		return REDIS_PERMISSION_KEY + id;
	}

}
