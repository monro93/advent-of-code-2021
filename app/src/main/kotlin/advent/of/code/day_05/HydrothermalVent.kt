package advent.of.code.day_05

class HydrothermalVent(
    private val start: Point,
    private val end: Point
) {

    fun getLine(): List<Point> {
        val points = mutableListOf<Point>()
        if(start.x == end.x) {
            for (i in getRangeBetweenInt(start.y, end.y)) {
                points.add(Point(start.x, i))
            }
        }else if (start.y == end.y) {
            for (i in getRangeBetweenInt(start.x, end.x)) {
                points.add(Point(i, start.y))
            }
        }

        return points
    }

    fun getLineWithDiagonals(): List<Point> {
        val points = mutableListOf<Point>()
        if(start.x == end.x) {
            for (i in getRangeBetweenInt(start.y, end.y)) {
                points.add(Point(start.x, i))
            }
        }else if (start.y == end.y) {
            for (i in getRangeBetweenInt(start.x, end.x)) {
                points.add(Point(i, start.y))
            }
        }else{
            val xRange = getRangeBetweenInt(start.x, end.x).toList()
            val yRange = getRangeBetweenInt(start.y, end.y).toList()
            for (i in xRange.indices) {
                points.add(Point(xRange[i], yRange[i]))
            }
        }
        return points
    }

    private fun getRangeBetweenInt(a: Int, b:Int): IntProgression {
        return if(a < b) {
            a..b
        }else {
            (b..a).reversed()
        }
    }

    override fun toString(): String {
        return getLine().toString()
    }

    data class Point(
        val x: Int,
        val y: Int
    )
}
