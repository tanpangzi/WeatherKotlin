package com.coodays.repairrent.utli

import java.math.BigDecimal

/**
 * Created by payne on 2018/2/23.
 */
class Arith {

    companion object {
        var DEF_DIV_SCALE = 10

        fun add(a: Double, b: Double): Double {
            val aa = BigDecimal(a)
            val bb = BigDecimal(b)
            return aa.add(bb).toDouble()
        }

        fun sub(a: Double, b: Double): Double {
            val aa = BigDecimal(a)
            val bb = BigDecimal(b)
            return aa.subtract(bb).toDouble()
        }

        fun mul(a: Double, b: Double): Double {
            val aa = BigDecimal(a)
            val bb = BigDecimal(b)
            return aa.multiply(bb).toDouble()
        }

        fun div(a: Double, b: Double): Double {

            return div(a, b, DEF_DIV_SCALE)
        }

        fun div(v1: Double, v2: Double, scale: Int): Double {
            if (scale < 0) {
                throw IllegalArgumentException(
                        "The scale must be a positive integer or zero")
            }
            val b1 = BigDecimal(java.lang.Double.toString(v1))
            val b2 = BigDecimal(java.lang.Double.toString(v2))
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
        }

    }
}