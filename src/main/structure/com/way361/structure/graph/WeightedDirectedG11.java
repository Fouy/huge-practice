package com.way361.structure.graph;

//【例7.5】  以Floyd算法求带权图每对顶点间的最短路径。
 //
public class WeightedDirectedG11                  //带权有向图G11
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E","F"};
        Edge edges[]={new Edge(0,1,17), new Edge(0,5,19), 
                      new Edge(1,2,18), 
                      new Edge(2,3,30), new Edge(2,4,11), 
                      new Edge(3,0,15), 
                      new Edge(4,1,4), new Edge(4,3,16), new Edge(4,5,13),
                      new Edge(5,2,12)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
        System.out.print("带权有向图G11，"+graph.toString());
//        for (int i=0; i<graph.vertexCount(); i++)
//            graph.shortestPath(i);               //顶点vi的单源最短路径，Dijkstra算法
        graph.shortestPath();                    //调用Floyd算法求带权图每对顶点间的最短路径
    }
}
/*
程序运行结果如下：
//Dijkstra算法
 //调用Floyd算法
带权有向图G11，顶点集合：(A, B, C, D, E, F) 
邻接矩阵:  
  0  17  ∞  ∞  ∞  19
  ∞  0  18  ∞  ∞  ∞
  ∞  ∞  0  30  11  ∞
  15  ∞  ∞  0  ∞  ∞
  ∞  4  ∞  16  0  13
  ∞  ∞  12  ∞  ∞  0
初值path数组
  -1  0  -1  -1  -1  0
  -1  -1  1  -1  -1  -1
  -1  -1  -1  2  2  -1
  3  -1  -1  -1  -1  -1
  -1  4  -1  4  -1  4
  -1  -1  5  -1  -1  -1
dist数组
  0  17  ∞  ∞  ∞  19
  ∞  0  18  ∞  ∞  ∞
  ∞  ∞  0  30  11  ∞
  15  ∞  ∞  0  ∞  ∞
  ∞  4  ∞  16  0  13
  ∞  ∞  12  ∞  ∞  0

以A作为中间顶点
(B,C)路径长度18，替换为(B,A),(A,C)路径长度199998？否
(B,D)路径长度99999，替换为(B,A),(A,D)路径长度199998？否
(B,E)路径长度99999，替换为(B,A),(A,E)路径长度199998？否
(B,F)路径长度99999，替换为(B,A),(A,F)路径长度100018？否
(C,B)路径长度99999，替换为(C,A),(A,B)路径长度100016？否
(C,D)路径长度30，替换为(C,A),(A,D)路径长度199998？否
(C,E)路径长度11，替换为(C,A),(A,E)路径长度199998？否
(C,F)路径长度99999，替换为(C,A),(A,F)路径长度100018？否
(D,B)路径长度99999，替换为(D,A),(A,B)路径长度32？是，d31=32，p31=0
(D,C)路径长度99999，替换为(D,A),(A,C)路径长度100014？否
(D,E)路径长度99999，替换为(D,A),(A,E)路径长度100014？否
(D,F)路径长度99999，替换为(D,A),(A,F)路径长度34？是，d35=34，p35=0
(E,B)路径长度4，替换为(E,A),(A,B)路径长度100016？否
(E,C)路径长度99999，替换为(E,A),(A,C)路径长度199998？否
(E,D)路径长度16，替换为(E,A),(A,D)路径长度199998？否
(E,F)路径长度13，替换为(E,A),(A,F)路径长度100018？否
(F,B)路径长度99999，替换为(F,A),(A,B)路径长度100016？否
(F,C)路径长度12，替换为(F,A),(A,C)路径长度199998？否
(F,D)路径长度99999，替换为(F,A),(A,D)路径长度199998？否
(F,E)路径长度99999，替换为(F,A),(A,E)路径长度199998？否
path数组
  -1  0  -1  -1  -1  0
  -1  -1  1  -1  -1  -1
  -1  -1  -1  2  2  -1
  3  0  -1  -1  -1  0
  -1  4  -1  4  -1  4
  -1  -1  5  -1  -1  -1
dist数组
  0  17  ∞  ∞  ∞  19
  ∞  0  18  ∞  ∞  ∞
  ∞  ∞  0  30  11  ∞
  15  32  ∞  0  ∞  34
  ∞  4  ∞  16  0  13
  ∞  ∞  12  ∞  ∞  0

以B作为中间顶点
(A,C)路径长度99999，替换为(A,B),(B,C)路径长度35？是，d02=35，p02=1
(A,D)路径长度99999，替换为(A,B),(B,D)路径长度100016？否
(A,E)路径长度99999，替换为(A,B),(B,E)路径长度100016？否
(A,F)路径长度19，替换为(A,B),(B,F)路径长度100016？否
(C,A)路径长度99999，替换为(C,B),(B,A)路径长度199998？否
(C,D)路径长度30，替换为(C,B),(B,D)路径长度199998？否
(C,E)路径长度11，替换为(C,B),(B,E)路径长度199998？否
(C,F)路径长度99999，替换为(C,B),(B,F)路径长度199998？否
(D,A)路径长度15，替换为(D,A,B),(B,A)路径长度100031？否
(D,C)路径长度99999，替换为(D,A,B),(B,C)路径长度50？是，d32=50，p32=1
(D,E)路径长度99999，替换为(D,A,B),(B,E)路径长度100031？否
(D,A,F)路径长度34，替换为(D,A,B),(B,F)路径长度100031？否
(E,A)路径长度99999，替换为(E,B),(B,A)路径长度100003？否
(E,C)路径长度99999，替换为(E,B),(B,C)路径长度22？是，d42=22，p42=1
(E,D)路径长度16，替换为(E,B),(B,D)路径长度100003？否
(E,F)路径长度13，替换为(E,B),(B,F)路径长度100003？否
(F,A)路径长度99999，替换为(F,B),(B,A)路径长度199998？否
(F,C)路径长度12，替换为(F,B),(B,C)路径长度100017？否
(F,D)路径长度99999，替换为(F,B),(B,D)路径长度199998？否
(F,E)路径长度99999，替换为(F,B),(B,E)路径长度199998？否
path数组
  -1  0  1  -1  -1  0
  -1  -1  1  -1  -1  -1
  -1  -1  -1  2  2  -1
  3  0  1  -1  -1  0
  -1  4  1  4  -1  4
  -1  -1  5  -1  -1  -1
dist数组
  0  17  35  ∞  ∞  19
  ∞  0  18  ∞  ∞  ∞
  ∞  ∞  0  30  11  ∞
  15  32  50  0  ∞  34
  ∞  4  22  16  0  13
  ∞  ∞  12  ∞  ∞  0

以C作为中间顶点
(A,B)路径长度17，替换为(A,B,C),(C,B)路径长度100034？否
(A,D)路径长度99999，替换为(A,B,C),(C,D)路径长度65？是，d03=65，p03=2
(A,E)路径长度99999，替换为(A,B,C),(C,E)路径长度46？是，d04=46，p04=2
(A,F)路径长度19，替换为(A,B,C),(C,F)路径长度100034？否
(B,A)路径长度99999，替换为(B,C),(C,A)路径长度100017？否
(B,D)路径长度99999，替换为(B,C),(C,D)路径长度48？是，d13=48，p13=2
(B,E)路径长度99999，替换为(B,C),(C,E)路径长度29？是，d14=29，p14=2
(B,F)路径长度99999，替换为(B,C),(C,F)路径长度100017？否
(D,A)路径长度15，替换为(D,A,B,C),(C,A)路径长度100049？否
(D,A,B)路径长度32，替换为(D,A,B,C),(C,B)路径长度100049？否
(D,E)路径长度99999，替换为(D,A,B,C),(C,E)路径长度61？是，d34=61，p34=2
(D,A,F)路径长度34，替换为(D,A,B,C),(C,F)路径长度100049？否
(E,A)路径长度99999，替换为(E,B,C),(C,A)路径长度100021？否
(E,B)路径长度4，替换为(E,B,C),(C,B)路径长度100021？否
(E,D)路径长度16，替换为(E,B,C),(C,D)路径长度52？否
(E,F)路径长度13，替换为(E,B,C),(C,F)路径长度100021？否
(F,A)路径长度99999，替换为(F,C),(C,A)路径长度100011？否
(F,B)路径长度99999，替换为(F,C),(C,B)路径长度100011？否
(F,D)路径长度99999，替换为(F,C),(C,D)路径长度42？是，d53=42，p53=2
(F,E)路径长度99999，替换为(F,C),(C,E)路径长度23？是，d54=23，p54=2
path数组
  -1  0  1  2  2  0
  -1  -1  1  2  2  -1
  -1  -1  -1  2  2  -1
  3  0  1  -1  2  0
  -1  4  1  4  -1  4
  -1  -1  5  2  2  -1
dist数组
  0  17  35  65  46  19
  ∞  0  18  48  29  ∞
  ∞  ∞  0  30  11  ∞
  15  32  50  0  61  34
  ∞  4  22  16  0  13
  ∞  ∞  12  42  23  0

以D作为中间顶点
(A,B)路径长度17，替换为(A,B,C,D),(D,A,B)路径长度97？否
(A,B,C)路径长度35，替换为(A,B,C,D),(D,A,B,C)路径长度115？否
(A,B,C,E)路径长度46，替换为(A,B,C,D),(D,A,B,C,E)路径长度126？否
(A,F)路径长度19，替换为(A,B,C,D),(D,A,F)路径长度99？否
(B,A)路径长度99999，替换为(B,C,D),(D,A)路径长度63？是，d10=63，p10=3
(B,C)路径长度18，替换为(B,C,D),(D,A,B,C)路径长度98？否
(B,C,E)路径长度29，替换为(B,C,D),(D,A,B,C,E)路径长度109？否
(B,F)路径长度99999，替换为(B,C,D),(D,A,F)路径长度82？是，d15=82，p15=0
(C,A)路径长度99999，替换为(C,D),(D,A)路径长度45？是，d20=45，p20=3
(C,B)路径长度99999，替换为(C,D),(D,A,B)路径长度62？是，d21=62，p21=0
(C,E)路径长度11，替换为(C,D),(D,A,B,C,E)路径长度91？否
(C,F)路径长度99999，替换为(C,D),(D,A,F)路径长度64？是，d25=64，p25=0
(E,A)路径长度99999，替换为(E,D),(D,A)路径长度31？是，d40=31，p40=3
(E,B)路径长度4，替换为(E,D),(D,A,B)路径长度48？否
(E,B,C)路径长度22，替换为(E,D),(D,A,B,C)路径长度66？否
(E,F)路径长度13，替换为(E,D),(D,A,F)路径长度50？否
(F,A)路径长度99999，替换为(F,C,D),(D,A)路径长度57？是，d50=57，p50=3
(F,B)路径长度99999，替换为(F,C,D),(D,A,B)路径长度74？是，d51=74，p51=0
(F,C)路径长度12，替换为(F,C,D),(D,A,B,C)路径长度92？否
(F,C,E)路径长度23，替换为(F,C,D),(D,A,B,C,E)路径长度103？否
path数组
  -1  0  1  2  2  0
  3  -1  1  2  2  0
  3  0  -1  2  2  0
  3  0  1  -1  2  0
  3  4  1  4  -1  4
  3  0  5  2  2  -1
dist数组
  0  17  35  65  46  19
  63  0  18  48  29  82
  45  62  0  30  11  64
  15  32  50  0  61  34
  31  4  22  16  0  13
  57  74  12  42  23  0

以E作为中间顶点
(A,B)路径长度17，替换为(A,B,C,E),(E,B)路径长度50？否
(A,B,C)路径长度35，替换为(A,B,C,E),(E,B,C)路径长度68？否
(A,B,C,D)路径长度65，替换为(A,B,C,E),(E,D)路径长度62？是，d03=62，p03=4
(A,F)路径长度19，替换为(A,B,C,E),(E,F)路径长度59？否
(B,C,D,A)路径长度63，替换为(B,C,E),(E,D,A)路径长度60？是，d10=60，p10=3
(B,C)路径长度18，替换为(B,C,E),(E,B,C)路径长度51？否
(B,C,D)路径长度48，替换为(B,C,E),(E,D)路径长度45？是，d13=45，p13=4
(B,C,E,D,A,F)路径长度82，替换为(B,C,E),(E,F)路径长度42？是，d15=42，p15=4
(C,D,A)路径长度45，替换为(C,E),(E,D,A)路径长度42？是，d20=42，p20=3
(C,D,A,B)路径长度62，替换为(C,E),(E,B)路径长度15？是，d21=15，p21=4
(C,D)路径长度30，替换为(C,E),(E,D)路径长度27？是，d23=27，p23=4
(C,E,D,A,F)路径长度64，替换为(C,E),(E,F)路径长度24？是，d25=24，p25=4
(D,A)路径长度15，替换为(D,A,B,C,E),(E,D,A)路径长度92？否
(D,A,B)路径长度32，替换为(D,A,B,C,E),(E,B)路径长度65？否
(D,A,B,C)路径长度50，替换为(D,A,B,C,E),(E,B,C)路径长度83？否
(D,A,F)路径长度34，替换为(D,A,B,C,E),(E,F)路径长度74？否
(F,C,D,A)路径长度57，替换为(F,C,E),(E,D,A)路径长度54？是，d50=54，p50=3
(F,C,D,A,B)路径长度74，替换为(F,C,E),(E,B)路径长度27？是，d51=27，p51=4
(F,C)路径长度12，替换为(F,C,E),(E,B,C)路径长度45？否
(F,C,D)路径长度42，替换为(F,C,E),(E,D)路径长度39？是，d53=39，p53=4
path数组
  -1  0  1  4  2  0
  3  -1  1  4  2  4
  3  4  -1  4  2  4
  3  0  1  -1  2  0
  3  4  1  4  -1  4
  3  4  5  4  2  -1
dist数组
  0  17  35  62  46  19
  60  0  18  45  29  42
  42  15  0  27  11  24
  15  32  50  0  61  34
  31  4  22  16  0  13
  54  27  12  39  23  0

以F作为中间顶点
(A,B)路径长度17，替换为(A,F),(F,C,E,B)路径长度46？否
(A,B,C)路径长度35，替换为(A,F),(F,C)路径长度31？是，d02=31，p02=5
(A,F,C,E,D)路径长度62，替换为(A,F),(F,C,E,D)路径长度58？是，d03=58，p03=4
(A,F,C,E)路径长度46，替换为(A,F),(F,C,E)路径长度42？是，d04=42，p04=2
(B,C,E,D,A)路径长度60，替换为(B,C,E,F),(F,C,E,D,A)路径长度96？否
(B,C)路径长度18，替换为(B,C,E,F),(F,C)路径长度54？否
(B,C,E,D)路径长度45，替换为(B,C,E,F),(F,C,E,D)路径长度81？否
(B,C,E)路径长度29，替换为(B,C,E,F),(F,C,E)路径长度65？否
(C,E,D,A)路径长度42，替换为(C,E,F),(F,C,E,D,A)路径长度78？否
(C,E,B)路径长度15，替换为(C,E,F),(F,C,E,B)路径长度51？否
(C,E,D)路径长度27，替换为(C,E,F),(F,C,E,D)路径长度63？否
(C,E)路径长度11，替换为(C,E,F),(F,C,E)路径长度47？否
(D,A)路径长度15，替换为(D,A,F),(F,C,E,D,A)路径长度88？否
(D,A,B)路径长度32，替换为(D,A,F),(F,C,E,B)路径长度61？否
(D,A,B,C)路径长度50，替换为(D,A,F),(F,C)路径长度46？是，d32=46，p32=5
(D,A,F,C,E)路径长度61，替换为(D,A,F),(F,C,E)路径长度57？是，d34=57，p34=2
(E,D,A)路径长度31，替换为(E,F),(F,C,E,D,A)路径长度67？否
(E,B)路径长度4，替换为(E,F),(F,C,E,B)路径长度40？否
(E,B,C)路径长度22，替换为(E,F),(F,C)路径长度25？否
(E,D)路径长度16，替换为(E,F),(F,C,E,D)路径长度52？否
path数组
  -1  0  5  4  2  0
  3  -1  1  4  2  4
  3  4  -1  4  2  4
  3  0  5  -1  2  0
  3  4  1  4  -1  4
  3  4  5  4  2  -1
dist数组
  0  17  31  58  42  19
  60  0  18  45  29  42
  42  15  0  27  11  24
  15  32  46  0  57  34
  31  4  22  16  0  13
  54  27  12  39  23  0

每对顶点间的最短路径如下：
(A,B)长度为17
(A,F,C)长度为31
(A,F,C,E,D)长度为58
(A,F,C,E)长度为42
(A,F)长度为19
(B,C,E,D,A)长度为60
(B,C)长度为18
(B,C,E,D)长度为45
(B,C,E)长度为29
(B,C,E,F)长度为42
(C,E,D,A)长度为42
(C,E,B)长度为15
(C,E,D)长度为27
(C,E)长度为11
(C,E,F)长度为24
(D,A)长度为15
(D,A,B)长度为32
(D,A,F,C)长度为46
(D,A,F,C,E)长度为57
(D,A,F)长度为34
(E,D,A)长度为31
(E,B)长度为4
(E,B,C)长度为22
(E,D)长度为16
(E,F)长度为13
(F,C,E,D,A)长度为54
(F,C,E,B)长度为27
(F,C)长度为12
(F,C,E,D)长度为39
(F,C,E)长度为23

*/

