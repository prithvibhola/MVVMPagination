package prithvi.io.mvvmpagination.utility.extentions

fun Number.roundOff(value: Int = 2) = String.format("%.${value}f".format(this))