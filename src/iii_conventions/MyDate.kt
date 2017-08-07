package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }

    operator fun plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}
operator fun TimeInterval.times(number:Int) = RepeatedTimeInterval(this, number)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current = dateRange.start

    override fun hasNext(): Boolean = current <= dateRange.endInclusive

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
}

data class RepeatedTimeInterval(val interval: TimeInterval, val number:Int)

operator fun MyDate.plus(intervals: RepeatedTimeInterval) = addTimeIntervals(intervals.interval, intervals.number)

