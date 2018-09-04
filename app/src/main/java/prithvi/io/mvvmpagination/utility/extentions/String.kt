package prithvi.io.mvvmpagination.utility.extentions

import java.security.NoSuchAlgorithmException

fun String.toMD5(): String {
    try {
        // Create MD5 Hash
        val digest = java.security.MessageDigest.getInstance("MD5")
        digest.update(this.toByteArray())
        val messageDigest = digest.digest()

        // Create Hex String
        val hexString = StringBuffer()
        for (i in messageDigest.indices)
            hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))

        return hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}