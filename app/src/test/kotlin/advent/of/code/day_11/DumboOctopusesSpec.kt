package advent.of.code.day_11

import advent.of.code.utils.splitByLineBreaks
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

class DumboOctopusesSpec : Spek({
    Feature("Test dumbo octopuses") {
        Scenario("Test howManyFlashes after step") {
            val testData = mapOf(
                """5483143223
                    2745854711
                    5264556173
                    6141336146
                    6357385478
                    4167524645
                    2176841721
                    6882881134
                    4846848554
                    5283751526
                """ to 10 to 204,
                """5483143223
                    2745854711
                    5264556173
                    6141336146
                    6357385478
                    4167524645
                    2176841721
                    6882881134
                    4846848554
                    5283751526
                """ to 100 to 1656,
                """3322874652
                    5636588857
                    7755117548
                    5854121833
                    2856682477
                    3124873812
                    1541372254
                    8634383236
                    2424323348
                    2265635842
                """ to 100 to 1613
            )

            testData.forEach { testCase ->
                lateinit var dumboOctopuses: DumboOctopuses
                Given("Some dumboOctopuses") {
                    dumboOctopuses = DumboOctopuses(
                        testCase.key.first
                            .splitByLineBreaks()
                            .filter { it.isNotEmpty() }
                            .map {
                                it.map {
                                        s -> s.toString().toInt()
                                }.toMutableList()
                            }
                    )
                }
                var result = 0
                When("howManyFlashesAfter ${testCase.key.second} Steps") {
                    result = dumboOctopuses.howManyFlashesAfterSteps(testCase.key.second)
                }

                Then("result should be the expected value") {
                    assertEquals(testCase.value, result)
                }
            }
        }
        Scenario("Test when is the first step all flashes") {
            val testData = mapOf(
                """5483143223
                    2745854711
                    5264556173
                    6141336146
                    6357385478
                    4167524645
                    2176841721
                    6882881134
                    4846848554
                    5283751526
                """ to 195,
                """3322874652
                    5636588857
                    7755117548
                    5854121833
                    2856682477
                    3124873812
                    1541372254
                    8634383236
                    2424323348
                    2265635842
                """ to 510
            )

            testData.forEach { testCase ->
                lateinit var dumboOctopuses: DumboOctopuses
                Given("Some dumboOctopuses") {
                    dumboOctopuses = DumboOctopuses(
                        testCase.key
                            .splitByLineBreaks()
                            .filter { it.isNotEmpty() }
                            .map {
                                it.map {
                                        s -> s.toString().toInt()
                                }.toMutableList()
                            }
                    )
                }
                var result = 0
                When("getting howManyStepsUntilAllFlashes") {
                    result = dumboOctopuses.howManyStepsUntilAllFlashes()
                }

                Then("result should be the expected value") {
                    assertEquals(testCase.value, result)
                }
            }
        }
    }
})
