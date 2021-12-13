package advent.of.code.day_12

import advent.of.code.utils.splitByLineBreaks
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

class PassagePathingSpec : Spek({
    Feature("Test passage pathing") {
        Scenario("Calculate number of paths pt1") {
            val testData = mapOf(
                """start-A
                    start-b
                    A-c
                    A-b
                    b-d
                    A-end
                    b-end
                """.trimIndent() to 10,
                adventData() to 4104
            )

            testData.forEach { testCase ->
                lateinit var passagePathing: PassagePathing
                Given("A passage pathing") {
                    passagePathing = PassagePathing(
                        testCase.key
                            .splitByLineBreaks()
                            .filter { it.isNotEmpty() }
                            .map {
                                val parts = it.split("-")
                                parts[0] to parts[1]
                            }
                    )
                }

                var result = 0
                When("Calculation how many paths") {
                    result = passagePathing.calculateNumberOfPaths(true)
                }

                Then("Result should be expected") {
                    assertEquals(testCase.value, result)
                }
            }
        }
        Scenario("Calculate number of paths pt2") {
            val testData = mapOf(
                """start-A
                    start-b
                    A-c
                    A-b
                    b-d
                    A-end
                    b-end
                """.trimIndent() to 36,
                adventData() to 119760
            )

            testData.forEach { testCase ->
                lateinit var passagePathing: PassagePathing
                Given("A passage pathing") {
                    passagePathing = PassagePathing(
                        testCase.key
                            .splitByLineBreaks()
                            .filter { it.isNotEmpty() }
                            .map {
                                val parts = it.split("-")
                                parts[0] to parts[1]
                            }
                    )
                }

                var result = 0
                When("Calculation how many paths") {
                    result = passagePathing.calculateNumberOfPaths(false)
                }

                Then("Result should be expected") {
                    assertEquals(testCase.value, result)
                }
            }
        }
    }
})


fun adventData() : String {
    return """
        he-JK
        wy-KY
        pc-XC
        vt-wy
        LJ-vt
        wy-end
        wy-JK
        end-LJ
        start-he
        JK-end
        pc-wy
        LJ-pc
        at-pc
        xf-XC
        XC-he
        pc-JK
        vt-XC
        at-he
        pc-he
        start-at
        start-XC
        at-LJ
        vt-JK
    """.trimIndent()
}
