package com.elies.springboot.datasource;

/**
 * @author 牟雪
 * @since 2018/4/13
 */
public class DynamicDataSourceHolder {

    public enum DataSourceType {
        write, read;
    }


    private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<DataSourceType>();

    public static void markWrite() {
        holder.set(DataSourceType.write);
    }

    public static void markRead() {
        holder.set(DataSourceType.read);
    }

    public static void reset() {
        holder.set(null);
    }

    public static boolean isChoiceNone() {
        return null == holder.get();
    }

    public static boolean isChoiceWrite() {
        return DataSourceType.write == holder.get();
    }

//    public static boolean isChoiceRead() {
//        return DataSourceType.read == holder.get();
//    }
}
