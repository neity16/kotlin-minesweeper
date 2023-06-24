package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.domain.BoardSize

class BoardSizeTest : StringSpec({

    "범위 내 위치하는 점을 입력하면 정상적으로 생성된다." {
        forAll(
            row(1),
            row(10),
            row(100),
            row(9),
            row(99),
            row(0),
        ) { size ->
            val boardSize = BoardSize(width = size, height = size)

            boardSize.area() shouldBe size * size
        }
    }

    "범위 밖에 위치하는 점을 입력하면 IllegalArgumentException 에러가 발생한다." {
        forAll(
            row(-1),
            row(-10),
            row(1000),
            row(101),
        ) { size ->
            val exception = shouldThrow<IllegalArgumentException> {
                BoardSize(width = size, height = size)
            }

            exception shouldHaveMessage "점은 ${0..100} 사이에 위치해야 합니다. 입력값 : $size, $size"
        }
    }
})