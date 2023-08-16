import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import kotlin.random.Random.Default.nextInt

/**
 * Example of a valid code: obno-9804-brdu.
 * Of 100 000 000 generated test codes, only an average of 8.95 valid codes per million generated codes.
 */
class CodeGenerator {
    private var validCombos1 = listOf("abt","bio","bor","goh","bue","bll","obr","bmt","ohr","obn","prv","vbi")
    private var validCombos2 = listOf("193","984","896","461","760","094","236","446","667","895","236","467")

    private var letters = "abcdefghijklmnopqrstuvxyz"

    fun generateCode(): String {
        var code = ""

        var validCombo1 = validCombos1[nextInt(0,validCombos1.size)]
        var validCombo2 = validCombos2[nextInt(0,validCombos2.size)]

        /**0*/code += letters[nextInt(0, letters.length)]
        /**1*/code += letters[nextInt(0, letters.length)]
        /**2*/code += letters[nextInt(0, letters.length)]
        /**3*/code += validCombo1[0]

        /**4*/code += "-"
        /**5*/code += validCombo2[0]
        /**6*/code += validCombo2[1]
        /**7*/code += nextInt(0, 10)
        /**8*/code += validCombo2[2]

        /**9*/code += "-"
        /**10*/code += validCombo1[1]
        /**11*/code += validCombo1[2]
        /**12*/code += letters[nextInt(0, letters.length)]
        /**13*/code += letters[nextInt(0, letters.length)]


        return code
    }

    fun verifyCodeIntegrity(code: String): Boolean {
        if (code.length != 14) return false

        val validCombo1 = code[3].toString() + code[10].toString() + code[11].toString()
        val validCombo2 = code[5].toString() + code[6].toString() +  code[8].toString()

        return validCombos1.contains(validCombo1) && validCombos2.contains(validCombo2)
    }

    private fun generateRandomCode(): String {
        var code = ""

        code += letters[nextInt(0,letters.length)]
        code += letters[nextInt(0,letters.length)]
        code += letters[nextInt(0,letters.length)]
        code += letters[nextInt(0,letters.length)]

        code += "-"
        code += nextInt(0,10)
        code += nextInt(0,10)
        code += nextInt(0,10)
        code += nextInt(0,10)

        code += "-"
        code += letters[nextInt(0,letters.length)]
        code += letters[nextInt(0,letters.length)]
        code += letters[nextInt(0,letters.length)]
        code += letters[nextInt(0,letters.length)]

        return code
    }

}
