package daytwo

enum class Shape(val abcMapping: Char, val xyzMapping: Char, val score: Int) {
    ROCK('A', 'X', 1) {
        override fun beats(shape: Shape): Result {
            return when (shape) {
                ROCK -> Result.DRAW
                PAPER -> Result.LOSS
                SCISSORS -> Result.WIN
            }
        }
    },
    PAPER('B', 'Y', 2) {
        override fun beats(shape: Shape): Result {
            return when (shape) {
                ROCK -> Result.WIN
                PAPER -> Result.DRAW
                SCISSORS -> Result.LOSS
            }
        }
    },
    SCISSORS('C', 'Z', 3) {
        override fun beats(shape: Shape): Result {
            return when (shape) {
                ROCK -> Result.LOSS
                PAPER -> Result.WIN
                SCISSORS -> Result.DRAW
            }
        }
    };

    abstract fun beats(shape: Shape): Result

    companion object {
        fun fromABCCHar(char: Char) : Shape {
           return values().first { shape -> shape.abcMapping == char }
        }

        fun fromXYZCHar(char: Char) : Shape {
            return values().first { shape -> shape.xyzMapping == char }
        }
    }
}