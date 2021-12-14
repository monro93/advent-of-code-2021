package advent.of.code.day_14

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

class PolymerizationSpec: Spek({
    Feature("Test Polymerization") {
        Scenario("Test getFrequencyNumber") {
            val testData = mapOf(
                firstInput() to 1588,
                adventData() to 2194
            )

            testData.forEach { testCase ->
                lateinit var polymerization: Polymerization
                Given("A polymerization") {
                    polymerization = Polymerization.create(testCase.key)
                }

                var result = 0
                When("Getting the frequencyNumber after 10 steps") {
                    result = polymerization.getFrequencyNumber(10)
                }

                Then("Result should be the expected one") {
                    assertEquals(testCase.value, result)
                }
            }
        }
        Scenario("Test getFrequencyNumberRecursive") {
            val testData = mapOf(
                firstInput() to 2188189693529L,
                adventData() to 2360298895777L
            )

            testData.forEach { testCase ->
                lateinit var polymerization: Polymerization
                Given("A polymerization") {
                    polymerization = Polymerization.create(testCase.key)
                }

                var result = 0L
                When("Getting the frequencyNumber after 40 steps") {
                    result = polymerization.getFrequencyNumberRecursive(40)
                }

                Then("Result should be the expected one") {
                    assertEquals(testCase.value, result)
                }
            }
        }
    }
})

fun firstInput(): String {
    return """NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
    """.trimIndent()
}

fun adventData(): String {
    return """
        OOBFPNOPBHKCCVHOBCSO

        NS -> H
        NN -> P
        FF -> O
        HF -> C
        KN -> V
        PO -> B
        PS -> B
        FB -> N
        ON -> F
        OK -> F
        OO -> K
        KS -> F
        FN -> F
        KC -> H
        NC -> N
        NB -> C
        KH -> S
        SV -> B
        BC -> S
        KB -> B
        SC -> S
        KP -> H
        FS -> K
        NK -> K
        OC -> H
        NH -> C
        PH -> F
        OS -> V
        BB -> C
        CC -> F
        CF -> H
        CP -> V
        VB -> N
        VC -> F
        PK -> V
        NV -> N
        FO -> S
        CK -> O
        BH -> K
        PN -> B
        PP -> S
        NF -> B
        SF -> K
        VF -> H
        HS -> F
        NP -> F
        SH -> V
        SK -> K
        PC -> V
        BO -> H
        HN -> P
        BK -> O
        BP -> S
        OP -> N
        SP -> N
        KK -> C
        HB -> H
        OF -> C
        VH -> C
        HO -> N
        FK -> V
        NO -> H
        KF -> S
        KO -> V
        PF -> K
        HV -> C
        SO -> F
        SS -> F
        VN -> K
        HH -> B
        OB -> S
        CH -> B
        FH -> B
        CO -> V
        HK -> F
        VK -> K
        CN -> V
        SB -> K
        PV -> O
        PB -> F
        VV -> P
        CS -> N
        CB -> C
        BS -> F
        HC -> B
        SN -> P
        VP -> P
        OV -> P
        BV -> P
        FC -> N
        KV -> S
        CV -> F
        BN -> S
        BF -> C
        OH -> F
        VO -> B
        FP -> S
        FV -> V
        VS -> N
        HP -> B
    """.trimIndent()
}
