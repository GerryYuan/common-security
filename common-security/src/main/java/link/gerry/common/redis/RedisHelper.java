package link.gerry.common.redis;

public class RedisHelper {

	static final String REDIS_PERMISSION_KEY = "_permission_";

	static final String REDIS_ROLE_KEY = "_role_";
	
	public static String getPermissionKey() {
		return REDIS_PERMISSION_KEY;
	}

	public static String getPermissionKey(Integer id) {
		return REDIS_PERMISSION_KEY + id;
	}

	public static String getRoleKey() {
		return REDIS_ROLE_KEY;
	}
	
}
