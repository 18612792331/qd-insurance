
package com.qding.api.mock;

import java.util.Random;

public class ValueGenerator {

    private static final Random random = new Random();

    public static Integer genInteger() {

        return random.nextInt(5);
    }

    public static Long genLong() {
        long ll= random.nextLong();
        if(ll < 0l){
            ll = 0l - ll;
        }
        return ll;
    }

    public static Byte genByte() {

        return (byte) (new Random().nextInt(Byte.MAX_VALUE));
    }

    public static Boolean genBoolean() {

        return random.nextBoolean();
    }

    public static Character genChar() {

        return (char) ('A' + random.nextInt(26));
    }

    public static Double genDouble() {

        return random.nextDouble();
    }

    public static Float genFloat() {

        return random.nextFloat();
    }

    public static Short genShort() {

        return (short) (new Random().nextInt(Short.MAX_VALUE));
    }

    public static String genString() {

        return "模拟内容" + genLong();
    }
    
}
