package github.haozi.lab.xxlboot.dto;

/**
 * @author wanghao
 * @Description 任务常量类
 * @date 2019-09-25 14:27
 */
public final class JobConstants {
    private JobConstants() {
    }

    public static final class GlueType {
        private GlueType() {
        }

        public static final String BEAN = "BEAN";
        public static final String GLUE_GROOVY = "GLUE_GROOVY";
        public static final String GLUE_SHELL = "GLUE_SHELL";
        public static final String GLUE_PYTHON = "GLUE_PYTHON";
        public static final String GLUE_PHP = "GLUE_PHP";
        public static final String GLUE_NODEJS = "GLUE_NODEJS";
        public static final String GLUE_POWERSHELL = "GLUE_POWERSHELL";
    }

    public static final class RouteStrategy {
        private RouteStrategy() {
        }

        public static final String FIRST = "FIRST";
        public static final String LAST = "LAST";
        public static final String ROUND = "ROUND";
        public static final String RANDOM = "RANDOM";
        public static final String CONSISTENT_HASH = "CONSISTENT_HASH";
        public static final String LEAST_FREQUENTLY_USED = "LEAST_FREQUENTLY_USED";
        public static final String LEAST_RECENTLY_USED = "LEAST_RECENTLY_USED";
        public static final String FAILOVER = "FAILOVER";
        public static final String BUSYOVER = "BUSYOVER";
        public static final String SHARDING_BROADCAST = "SHARDING_BROADCAST";
    }

    public static final class BlockStrategy {
        private BlockStrategy() {
        }

        public static final String SERIAL_EXECUTION = "SERIAL_EXECUTION";
        public static final String DISCARD_LATER = "DISCARD_LATER";
        public static final String COVER_EARLY = "COVER_EARLY";
    }

}
