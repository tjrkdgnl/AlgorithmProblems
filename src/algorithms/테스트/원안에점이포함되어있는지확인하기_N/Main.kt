package algorithms.테스트.원안에점이포함되어있는지확인하기_N

/***
 * 좌표가 원 안에 포함되어있는지 계산하는 방법은 두 점 사이의 거리 공식을 이용한다.
 * 따라서 원의 중심좌표와 주어진 임의의 좌표의 거리를 구한 뒤, 이것이 반지금보다 작으면 내포되어 있음을 의미한다.
 *
 * 두 점을 구하는 공식은 피타고라스 원리에 의해 A^2 + B^2 = C^2 -> (X-x)^2 + (Y-y)^2 = 거리^2 로 나타낼 수 있다.
 * 이 문제에서는 (X-x)^2 + (Y-y)^2인 거리가 R^2보다 작아야하므로
 *
 * (X-x)^2 + (Y-y)^2 <= R^2 인 경우 임의의 좌표는 원 안에 포함된 좌표이다.
 */
fun main() {
    /**
     * 모든 아이콘의 반지금이 R = 20이고 아이콘의 중심 좌표가 (A[i],B[i])로 배열의 size 개수만큼 주어졌을때,
     * touch한 (x,y) 좌표로 인해 선택되는 icon의 번호를 구하여라
     */
    val A = intArrayOf(100, 150, 100, 50)
    val B = intArrayOf(100, 100, 70, 120)
    val R = 20

    val x = 100
    val y = 70


    for (i in 0 until A.size) {
        if (R * R >= Math.pow((A[i] - x).toDouble(), 2.0) + Math.pow((B[i] - y).toDouble(), 2.0)) {
            println(i)
            return
        }
    }

    println(-1)
}