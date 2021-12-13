package advent.of.code.day_10

import advent.of.code.utils.splitByLineBreaks
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

class SyntaxScorerSpec: Spek({
    Feature("Test Syntax scorer") {
        Scenario("Test corrupted lines score") {
            val testData = mapOf(
                "{([(<{}[<>[]}>{[]{[(<()>" to 1197,
                """
                    [({(<(())[]>[[{[]{<()<>>
                    [(()[<>])]({[<{<<[]>>(
                    {([(<{}[<>[]}>{[]{[(<()>
                    (((({<>}<{<{<>}{[]{[]{}
                    [[<[([]))<([[{}[[()]]]
                    [{[{({}]{}}([{[{{{}}([]
                    {<[[]]>}<{[{[{[]{()[[[]
                    [<(<(<(<{}))><([]([]()
                    <{([([[(<>()){}]>(<<{{
                    <{([{{}}[<[[[<>{}]]]>[]]
                """ to 26397,
                adventData() to 315693
            )

            lateinit var syntaxScorer: SyntaxScorer
            Given("a syntaxScorer") {
                syntaxScorer = SyntaxScorer()
            }

            testData.forEach {testCase ->
                var result = 0
                When("getting the corrupted score") {
                    result = syntaxScorer.getScoreOfCorruptedLines(testCase.key.splitByLineBreaks().filter { it.isNotEmpty() })
                }

                Then("result should be the expected one") {
                    assertEquals(testCase.value, result)
                }
            }
        }

        Scenario("Test incomplete lines score") {
            val testData = mapOf(
                """
                    [({(<(())[]>[[{[]{<()<>>
                    [(()[<>])]({[<{<<[]>>(
                    {([(<{}[<>[]}>{[]{[(<()>
                    (((({<>}<{<{<>}{[]{[]{}
                    [[<[([]))<([[{}[[()]]]
                    [{[{({}]{}}([{[{{{}}([]
                    {<[[]]>}<{[{[{[]{()[[[]
                    [<(<(<(<{}))><([]([]()
                    <{([([[(<>()){}]>(<<{{
                    <{([{{}}[<[[[<>{}]]]>[]]
                """ to 288957L,
                adventData() to 1870887234
            )

            lateinit var syntaxScorer: SyntaxScorer
            Given("a syntaxScorer") {
                syntaxScorer = SyntaxScorer()
            }

            testData.forEach {testCase ->
                var result = 0L
                When("getting the incomplete score") {
                    result = syntaxScorer.getScoreOfIncompleteLines(testCase.key.splitByLineBreaks().filter { it.isNotEmpty() })
                }

                Then("result should be the expected one") {
                    assertEquals(testCase.value, result)
                }
            }
        }
    }
}
)

fun adventData(): String {
    return """
        ([{{(([{<<([<[{}{}][{}<>]>{(()())(<>)}]{[(()[])<[][]>]<[()[]]<{}<>>>}){{<<[]<>>(()[])>{({}(
        {<({[{[({(<[({[][]}(()<>))(({}<>)<()[]>)][<{{}[]}[{}()]><[(){}][<><>]>]><{{({}<>){<><>}}[(<><
        <{({{{{{{<[([([]<>)]{({}())<()()>})]<[([<>[]][()[]]){[(){}]<[]{}>}]{<[<>{}][(){}]><{[][]}{<><>}>}>>}<<
        ([([{[[{{[((((()[]))<[()<>]{(){}}>))[[({()()}{[][]})(({}[])(<>{}))][{<(){}><(){}>}]]]<[[{[<>[]]}
        {{[{{{<(([{[<{[]{}}<[]{}>>([<>[]][{}[]])]<[(<>[])[<>[]]]({[]<>]{()<>})>}{{<<[]()>[<>[]]>({()<>}<[]<>>)}[
        <<{[<[<<[{[[<<<>[]>[[][]]>]]}]>[({[[{([]<>){[]()}}{{()<>}[{}()]}][[(()<>)(<>{})]{[<><>]({}<
        {[[<<({<[[[<[[<>{}]{{}[]}]<(<>())>>]]>{<{{<[{}[]][<>[]]>[(<><>)<(){}>]}<(<{}[]><{}()>)([()<>]
        ([<({<(<{[<<<({}{})({})>((()()))>[(<[]{}>{[]()}>]>]}([({<<<>[]>[{}()]><{[]()}(<>[])>})[([[[][]][[]()]]({()}
        [{<{<<{[<({{{[[]()]<[]{}>}(<(){}>([]{}))}}[[<<[]()>{()<>}>]{[<()>({}{})]({<><>}([]{}))}])[<[<
        ({<<<{<[[(<[<[[][]]([]<>)><{<>{}}[{}{}]>]>{[<[{}]>({[][]}<()()>)]<[{<>[]}[[]{}]]>}){[<[(())<{}{}>]<(<>())[[]{
        <(<<(({<<[((<[[]()]{{}{}}>[<<>{}>([]<>)]){{<[]<>>([]<>)}<([]())<{}>>})<[[(()())]([{}()](<>
        [[<{([({[{{{<{()()}{{}{}}>{[[]<>](()())}}}<[<(<><>)(<><>)>{{{}<>}(<>())}]{(<<>()>([]{}))({()<>}[<>{}])}
        <{{(<<{{([<[<{()[]}>][<<{}<>>>[([]<>)({}[])]]>])}<{(<<([[]{}])<[[]{}]{{}{}}>><{{(){}}<()<>>}([{}{}][
        [[<(([{((<{([{{}<>}[<>[]]]<[()()]<[][])>)}[<{[[]]<{}<>>}([<><>]{[][]})>]>(<[<<<><>>>(<<>{}>
        {{{{[[<<<{{<({{}}<()<>>)[{(){}}(<>())]>{[<<>[]>{()()}]{(()[]){<><>}}}}<{([(){})){{[]}<{}{}
        (<{<[<<{(<{({<{}()>{<>{}}}{[()[]]{<>[]}})<({{}}{<><>})[<(){}>[<><>]]>}>)}>>]><<({<[{([{{()<>}[[]{}
        [{{<{(({[<([[[()][<><>]][[<>[]]<<>()>]]<[{{}[]}({}<>)]([[][]]<<>{}})>)>]})(<([([(([][])[()<>]){[<>[]]({}
        [[{[{{[[((<{<{<>()}([]<>)>([<>{}]<[]()>)})({[{<>[]}<{}<>>](<(){}>(<>{}))}({((){})({}<>)}<<<>
        <{(<<<{[[(<(<[{}()]{[][]}>[{<>}[<>{}]])((<()()>[[]{}]))>([<([][]){[]<>}>([(){}]{(){}})]<({[
        [(([([[{(([{[{<>{}}<{}<>>][[(){}]([]{})]}([<[]<>>{[][]}][[{}]{[]()}])})<<({({}{})<[]{}>}<{{}<>}<{}[]>>
        <({<<<<({<[{[<{}()>](<<>{}>[[]<>])}]<[{(()<>)([]())}(({}<>){{}<>})]{({(){}}[{}[]]>({<><>}<{}{}>)}>><{<<(
        [[<<<(<{([(<<{{}()}<{}()>>(([][])[[]{}])>{<{<>[]}><[<>{}]>})(([[<>{}]([]())]))]>}<<{<{(([]{}){()<>})<<{}<>>
        <{<[{[<([(<<{{{}()}({}())}<((){})<[]{}>}>({<[]()><()<>>}[{()()}[<>]])>(([{<>()}<<>()>][([][])])(((()[]))<((
        <{[<{{[<(<<{[([][])<{}[]>]<<[]<>>>}{({<>()}<[]<>>){{()()}{[]{}}}}>>[<<<({}[])>{[{}[]](()[])}>>
        (<(<[{{{[((({[{}{}]<<>()>}]{([<>[]](()[]))})[[{<<>[]>[{}()]}[[{}[]][{}[]]]][{(<><>)[<><>]}[<<><>>[
        ([([<[<{<[<<[<[]{}>({}())][<{}[]>({})]>[({[]}{[]<>})<<{}[]>[{}{}]>]>[[{<{}()>[<><>]}{([]<>)([]())}]{(
        ({[((({(<[{[([{}<>](()<>))[(()())[()()]]]}]>){({(<<(()())[[]<>]>{{(){}}{<>}}>(<(<>())[(){}]>))<{{[<>()][(
        {<{<{({<([({<(<><>){<><>}>(<[][]>{<>()})}<<([]()){<>()}><([][])[(){}]>>){(<<()()>}<{{}{}}({}{})>)((<{}{}>(
        <{{(<([{<(([(<{}<>>[{}{}])([[][]]{[]<>})]({<()[]>})){<(([]<>)({}[]))><[({}<>)]([()<>](()()))>})>[<{(<<[](
        <{<<(({{<{<{([<><>]({}[]))[<<>[]>(()<>)]}{[{()}][<[]>{{}()}]}>}<<[{{(){}}{()[]}}[<[]<>>{[]<>}]]([<{}{}>{<>[]
        <<<[[((<({<<(([][])<<>[]>)[[[]<>]<{}{}>]>({{()<>}}{<[]{}><()<>>})>}<(<<([]<>)<[]>>{[<><>]}>[[[()[]]<{}{}>]({[
        [[({{((({(<(<([]{})([]{})>({{}[]}([]()]))>({<{(){}}[[][]]>(([]{})[<><>])}([{[][]}<<>()>])))[(<<[[]<>][{}<>]>
        ({[{{(({{[<(<[<>{}]((){})><[<><>]([]())>)<{({}<>)<{}()>}{{(){}}<[]{}>}>>[{{<<>{}>{{}}}<[{}[]][{
        {[{{(<(([([{[[(){}](()<>)]<[[][]]([][])>}]{{(((){})([]<>))(([]<>)[()<>])}{[<{}[]>]<[<><>](<>())>}
        ({({{[<<{((((<[]{}>{<><>})))({<<<>{}>({}())>}<<<[]()>{{}()}>[<{}[]>[<><>]]>))[(<(({}[])<<>()>>({{}<>}<[]<
        {{[{<((<{<(({[()()]{{}[]}}{([]()){<>{}}>)<[[[][]]({}[])]>)<({(()<>)[<>]})<[[()<>]]<[()()]<[]<>>>>>>{[((
        [(<<<<<[<(([[{()()}[()<>]]]{(<{}{}>{{}[]})<<[]()>([][])>}){[{<[]()>{{}<>}}{[[][]][{}]>]})>]<{{(<<[[]{}
        (({[<[<([[<({[{}<>]}[(()()){<>{}}])<(<<>()>)[(()<>){{}{}}]>>]{<(([()()][[]<>]){{[]{}}([][])})>}][[
        [<((<{([(({{{{{}}{[]()}}<{()<>}{(){}}>}}[(<<{}{}>><[<><>]{[]<>}>)<{<<><>>{()<>}}<[()<>]{[]{}}>>]))]{(({{
        {[[<([<[{{(<[([]<>)([]<>)][[<>{}]]>){({{()<>}<[]{}>}<{<>{}}(<><>)>)>}{<<{((){}){<>{}}}<[<><>]([]())>
        {[{([<<{<((<(<(){}>([]()))<{()<>}<{}()>>><<[{}()]{()()}>>)[({<{}{}>{{}<>}}<[[]()](<>())>)(([()<>][[]<
        <<[<{[<<[<<(<<()>{()<>}>{[[]<>]<<>[]>})>{<<{<>()}(()<>)><(()<>)<()<>>>>{[([]())([]<>)][[{}()]
        ({<{<[<{{([[[{[]()}[[]<>]]][<<{}{}><()()>>[({}[])(<>{})]]>)[{(((()<>)([][]))){<<[]>(()())>}}((
        [<{<<({(((<<<[()<>][()<>]>>[(<[][]>[[]<>])]>){{[((()<>)<{}<>>)[[<>[]]((){})]]][([{{}()}[[]]](<<>
        <{{{[<(([([[<({}<>)[<><>]>([[][]](()))]]]])(<{([<[()<>][<>[]]>{[<>{}]([]<>)}](<{(){}}<()()>><<()()>[[]{}]
        {{[({([{{({<((()<>){()[]})>[[{<>[]}]{[{}<>]{<>()}}]}<<<<[]>[{}[]]>{({}<>)[()<>]}>(<[<>()]{<>[]}>)>)
        <[<(([{{(<([(([]<>)([]()))<(<>{}){{}<>}>][[<[]{}>{<>{}}]{<<>{}>}])([[<{}{}>(<>{})]<[[]()>>]<([(){}]([]()))
        <[{(([<{{<{{<[()()]><(<>())>}}<{[<()()>]<[()[]][()[]]>}<([()[]]({}<>)){{<>[]}[<>()]}>>}}[(<<[([]()){{}
        <{{[<<[[<([<<[()[]][()<>]>(<<>[]>[()<>])>])([{{[[]{}]<{}[]>}({<>[]})}]<<[(<>[])([][])][<<>()>]>>)><[
        <<{{[({{{{<[<{[]<>}({}{})>{(<>())(<>[])}]({([][])(()<>)})>(<{([][])<[][]>}([[]]<()()>]>{{[()
        [[{[{([[[{[{[([]<>){<>{}}](<<>()>)}{[<[]{}>[{}[]]]}]}[([([<>()])]<<[<>][[]<>]>{({})[<>{}]}>]<({
        (([[{(<{{<([[(<>){<>()}]<[[]{}]<()<>>>]{({{}[]}<<>{}>)<([]{})[(){}]>})>({<(({}[])<()<>>)>}((<<()()>
        <{(<<(<[<{{<<{()[]}{{}()}><<<>][[]{}]>>([([]<>)<<>{}>])}[{[<{}[]>]<<{}[]>{<>[]}>}]}[{{{<<><>>{()<>}}}
        (({(<<((<{<(<<()>([]())>){({[][]}<[]()>){<<>{}>([])}}>{(<([][])<<>()>>)}}{[<((<><>)([]<>)){(()[])}>{<<[
        {[{[([((((([([[]{}][()[]])<(()())[<>[]]>]))[([[<<>()>{{}<>}](({}<>))][[({}[])]])]))[{({[[<<><>>][(()<>)<<>()
        <<(<([([({(({{{}()}(())}[(<>[])({}<>)])([([][]){[]<>}]{{[][]}[(){}]})){(<<<>()>[[][]]><<[][]>
        [<<{{{({[<{[<[[]{}]<{}<>>>[[[]<>][<>{}]]]<{{<>{})({}[])}>}([{<<>()><{}()>}]([{{}[]}{[]<>}][(<>())[[]{}]]))
        [([({{[<{<<((([]{})({}<>))[<<>[]><[]{}>])><<<((){})[()()]>>[{<{}{}><<>[]>}<<<>><<>{}>>]>>[{(
        [({{(<{<([({[<<><>>({}[])]({<>()}<{}[]>]})])>}><([<<<[{<<><>>[[][]]}<[{}[]]<<><>>>]([{()<>
        <{<<[((<{<{<<{[]<>}{[][]}>{[{}[]][<>{}]}>[{{[]<>}<<>[]>}<[<>()][()<>]>]}(<{(<>[])(<>())}>{{(()[]
        [[[{({<(({[{([<>{}]{<>})(([]{})(()[]))}]}<[((<<>{}}[<><>])<<{}[]>>)(<<(){}>(()<>)>[<()()>([]<>)])]({([{}[]]
        {{<[<[({[([[{[{}{}][[]()]}{(()())}]{[<()()>{{}<>}]({[]<>}[()()])}])]{[<{[<()[]>]}({[<>{}][(){}]}[<[]<>>[()[]
        {[{{{([(<([(<{()}[{}()]>{{<>{}}{(){}}})[<<<>{}><<><>>>]])<([(<{}[]>)][(<()[]}[()<>])[(()())<{}[]>]])[(
        <({([{<<<<{[([<>{}]<{}{}>)(<[]<>>[()<>])]}>><[{[<[[]{}]{(){}}>[<()[]][<>]]](<[[]()]([]{})>)}({((<><>))<{<><>}
        [({<[{<<(([<({[]<>}<()()>)[{<>()}]>>{[[<<><>>{[][]}]([{}()](<>[]))]}))>>}]({(<{[(<<(()[])[{}<>]>
        <([<<(({<{[[[{{}<>}([])]<{<><>}>]<<({}<>)<<><>>><<[]<>]<{}[]>>>]}{[{<{()}{()}>(<[]{}>{<>[]})}[(<<><>>{<>()})[
        ({<{[(<<[<{([{<>{}}{<>()}]{{[][]}[[]<>]})}{({(<>())(())}<{()()}<()()>>){<({}{})<{}{}}>[({}<>
        {{<<<[({({[{<({}())({}())>}{<{{}[]}[[]()]><(()<>)(<><>)>})})})<<<{{(<{()<>}{(){}}><{()()}([]<>)>)([(
        {{[{[[[({<{{[(<>)[()[]]]}<<{<>[]}[<>[]]><[<>]<{}>>>}({[{[][]}([]())]}[[{{}{}}[()[]]]<[(){}]>])
        <{[([{((<[[<<<<><>><{}()>>[<{}()><<>[]>]>[<<{}()>{{}<>}><<{}<>>{[][]}>]]<[{({}[])}{[{}<>][<>
        [{<(([<{{{([{{<>{}}{(){}}}<[{}()]<<>()>)][{[{}{}]({}<>)}{(<>[])<()[]>}])<<[{{}[]}[[][]]]{[{}<>]<
        ({{[[[{<([({[<()<>><{}[]>][([]{})({}())]}<[<[]{}>]{{[]{}}({}{})}>)<{[[[]()]<[]{}>][[<>[]]{[][]}]}([{[][]}<[]
        <((({{{[{(<{<[()[]]<{}[]>>[{(){}}({}[])]}<<([])<(){}>>(([]<>)[<>[]])>>)}<([({(()<>)<()<>>}{<[][]>[[][
        [<({<[{[[{{(<[<>{}]({}())>({{}{}}{[]()})){[(<>()){()()}]{([][])[<>{}]}}}([<[[]()]([])>([[]()]<<><>>)]([<<>()>
        [<{[(<(<<({<{<{}()>[<>{}]}{[{}{}]{<>[]}}>{[{<><>}<[]{}>](({}<>)([]{}))}}([{<<>{})({}<>)}<{{}<>}{{}{}}>])){<[
        [{<{{{{{{{<<({[][]}([]<>))[<()[]><<>[]>]><<(<><>)[{}[]]>((()[]))>>{{({<>[]}<<>()>)[[<>()]<<>{}>]}}}[<(
        <(<[((<{[[[<[[<><>]([]())]{{()[]}([][])}>]](([([{}]{{}()}){[<><>]<[]<>>}]<((<>{}){{}<>})<{
        ([[(((((<<{({<{}[]>((){})}(<{}<>>[()[]])){<<{}()>(<>())>[({}[])<<>()>]}}>(<(<(()[])[[]()]>[<(
        {({{{{<[<<(<<[[]()]{{}[]}>[<[]<>>[[]<>]]>(<<()<>>(<>())><([]<>)>})><<<({{}{}}<[]()>){{()()}[{}[]]}
        [<[[({({[{[(({<>[]}{()<>})[({}())[[]()]]){[{()<>}(<>)](<<>>)}]}][(([[<{}()>]{([]{}){[]}}])[
        (<{[<({[<<{{(<{}<>><<>{}>)[[{}()][{}[]]]}<[{[][]}](([]{})(()[]))>}<([{<><>}[[][]]][<[]{}><[]{}>]}(<{()
        [(<(<([[[[<(({()[]}<<>()>)[[()<>]({}<>)])>]]]{(([(<(()<>)[[]<>]>{[<>()]{<>{}}})({<()()>}<<<><>>
        <(<[<{([<[{{<{()}[<><>]>(<()[]>(<><>))}[[<{}{}><[][]>]<((){})[<><>]>]}(((<[][]><[]{}>)[[{}{}]{()[]}]){[(()()
        ({<[<[{({{{(({{}<>}{<>[]})<{()[]}<{}{}>>)[((()<>)<<><>>]<[{}]<<><>>>]}[({[{}()][[]{}]}[[<><
        [[(({[{[[<{[{({}())<<>[]>}{{{}[]}<(){}>}]{<[(){}]<{}<>]>}}([{{()[]}<<>()>}([()[]]<[]>)][<([]<>)[[]{
        [<(<((({[[{([{<>[]}<<>()>]([[]()]))}]([(<{()[]>[{}{}]><{()()}<()[]>>)](<<[()[]]{<><>}>[<()[]>(<
        ({({[<{<(<(([[[]{}]({}<>)])<{{{}{}}<{}{}>}{{{}<>}[{}()]}>)((<[<><>][(){}]><[{}<>](()())>)({{<
        [{({({(<(<{({<<>[]><{}>}<<{}{}>{<>{}}>)(<[{}[]]([]())>{<(){}>{<><>}})}<{[{<>}{[]{}}]([{}{}]<<><>>)
        ({{[({[<[<({[[[]{}](()())])<{<(){}>[()()]}<[<>()][(){}]>>)>({<<[[][]]<<><>>><<()[]>>><<<()[]>{[]()}
        ({({{[[{((([{({}()){<>()}}]([[<>{}]<()[]>][[()]<[]<>>]))))}]]}[<({[<<{<[<>()]<<>{}>}<<{}{}>[<>()]>}([(()
        [[{([{(<[{((({(){}])(<{}<>>[<>{}]))<<(<>())<[]{}>><(()[])<()[]>>>)([({<><>}<<>{}>)({<>{}}(<>{}))])}]>(
        ([<<[<[[({{[[({}{}){()<>]]{[[]<>]}]({<()()>}{<[][]>})}})]({<{({[{}[]][()[]]}[{{}{}}{(){}}])<([[][]]{<>{}})
        [{[{[({<([{[({()<>}{{}{}})<<[]{}>>][({{}[]}[()[]])[({}{})<<>{}>]]}]{{{<([]())(<>[])><<[]()>({}{})>}}((<<(){}>
        (<(<{({{<{[{([{}()]{(){}})[[<>{}](<><>)]})(<[{{}()}]<[[][]]{()<>}>><{{[]<>}{<>()}}{<{}<>>[[]<>]}>)}>}[[<(
    """.trimIndent()
}