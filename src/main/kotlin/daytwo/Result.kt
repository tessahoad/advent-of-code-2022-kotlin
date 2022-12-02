package daytwo

enum class Result(val score: Int, val xyzMapping: Char) {
    WIN(6, 'Z'),
    DRAW(3, 'Y'),
    LOSS(0, 'X');

    companion object {
        fun fromXYZCHar(char: Char) : Result {
            return Result.values().first { shape -> shape.xyzMapping == char }
        }
    }
}