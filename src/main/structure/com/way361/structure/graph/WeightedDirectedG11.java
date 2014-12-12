package com.way361.structure.graph;

//����7.5��  ��Floyd�㷨���Ȩͼÿ�Զ��������·����
 //
public class WeightedDirectedG11                  //��Ȩ����ͼG11
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
        System.out.print("��Ȩ����ͼG11��"+graph.toString());
//        for (int i=0; i<graph.vertexCount(); i++)
//            graph.shortestPath(i);               //����vi�ĵ�Դ���·����Dijkstra�㷨
        graph.shortestPath();                    //����Floyd�㷨���Ȩͼÿ�Զ��������·��
    }
}
/*
�������н�����£�
//Dijkstra�㷨
 //����Floyd�㷨
��Ȩ����ͼG11�����㼯�ϣ�(A, B, C, D, E, F) 
�ڽӾ���:  
  0  17  ��  ��  ��  19
  ��  0  18  ��  ��  ��
  ��  ��  0  30  11  ��
  15  ��  ��  0  ��  ��
  ��  4  ��  16  0  13
  ��  ��  12  ��  ��  0
��ֵpath����
  -1  0  -1  -1  -1  0
  -1  -1  1  -1  -1  -1
  -1  -1  -1  2  2  -1
  3  -1  -1  -1  -1  -1
  -1  4  -1  4  -1  4
  -1  -1  5  -1  -1  -1
dist����
  0  17  ��  ��  ��  19
  ��  0  18  ��  ��  ��
  ��  ��  0  30  11  ��
  15  ��  ��  0  ��  ��
  ��  4  ��  16  0  13
  ��  ��  12  ��  ��  0

��A��Ϊ�м䶥��
(B,C)·������18���滻Ϊ(B,A),(A,C)·������199998����
(B,D)·������99999���滻Ϊ(B,A),(A,D)·������199998����
(B,E)·������99999���滻Ϊ(B,A),(A,E)·������199998����
(B,F)·������99999���滻Ϊ(B,A),(A,F)·������100018����
(C,B)·������99999���滻Ϊ(C,A),(A,B)·������100016����
(C,D)·������30���滻Ϊ(C,A),(A,D)·������199998����
(C,E)·������11���滻Ϊ(C,A),(A,E)·������199998����
(C,F)·������99999���滻Ϊ(C,A),(A,F)·������100018����
(D,B)·������99999���滻Ϊ(D,A),(A,B)·������32���ǣ�d31=32��p31=0
(D,C)·������99999���滻Ϊ(D,A),(A,C)·������100014����
(D,E)·������99999���滻Ϊ(D,A),(A,E)·������100014����
(D,F)·������99999���滻Ϊ(D,A),(A,F)·������34���ǣ�d35=34��p35=0
(E,B)·������4���滻Ϊ(E,A),(A,B)·������100016����
(E,C)·������99999���滻Ϊ(E,A),(A,C)·������199998����
(E,D)·������16���滻Ϊ(E,A),(A,D)·������199998����
(E,F)·������13���滻Ϊ(E,A),(A,F)·������100018����
(F,B)·������99999���滻Ϊ(F,A),(A,B)·������100016����
(F,C)·������12���滻Ϊ(F,A),(A,C)·������199998����
(F,D)·������99999���滻Ϊ(F,A),(A,D)·������199998����
(F,E)·������99999���滻Ϊ(F,A),(A,E)·������199998����
path����
  -1  0  -1  -1  -1  0
  -1  -1  1  -1  -1  -1
  -1  -1  -1  2  2  -1
  3  0  -1  -1  -1  0
  -1  4  -1  4  -1  4
  -1  -1  5  -1  -1  -1
dist����
  0  17  ��  ��  ��  19
  ��  0  18  ��  ��  ��
  ��  ��  0  30  11  ��
  15  32  ��  0  ��  34
  ��  4  ��  16  0  13
  ��  ��  12  ��  ��  0

��B��Ϊ�м䶥��
(A,C)·������99999���滻Ϊ(A,B),(B,C)·������35���ǣ�d02=35��p02=1
(A,D)·������99999���滻Ϊ(A,B),(B,D)·������100016����
(A,E)·������99999���滻Ϊ(A,B),(B,E)·������100016����
(A,F)·������19���滻Ϊ(A,B),(B,F)·������100016����
(C,A)·������99999���滻Ϊ(C,B),(B,A)·������199998����
(C,D)·������30���滻Ϊ(C,B),(B,D)·������199998����
(C,E)·������11���滻Ϊ(C,B),(B,E)·������199998����
(C,F)·������99999���滻Ϊ(C,B),(B,F)·������199998����
(D,A)·������15���滻Ϊ(D,A,B),(B,A)·������100031����
(D,C)·������99999���滻Ϊ(D,A,B),(B,C)·������50���ǣ�d32=50��p32=1
(D,E)·������99999���滻Ϊ(D,A,B),(B,E)·������100031����
(D,A,F)·������34���滻Ϊ(D,A,B),(B,F)·������100031����
(E,A)·������99999���滻Ϊ(E,B),(B,A)·������100003����
(E,C)·������99999���滻Ϊ(E,B),(B,C)·������22���ǣ�d42=22��p42=1
(E,D)·������16���滻Ϊ(E,B),(B,D)·������100003����
(E,F)·������13���滻Ϊ(E,B),(B,F)·������100003����
(F,A)·������99999���滻Ϊ(F,B),(B,A)·������199998����
(F,C)·������12���滻Ϊ(F,B),(B,C)·������100017����
(F,D)·������99999���滻Ϊ(F,B),(B,D)·������199998����
(F,E)·������99999���滻Ϊ(F,B),(B,E)·������199998����
path����
  -1  0  1  -1  -1  0
  -1  -1  1  -1  -1  -1
  -1  -1  -1  2  2  -1
  3  0  1  -1  -1  0
  -1  4  1  4  -1  4
  -1  -1  5  -1  -1  -1
dist����
  0  17  35  ��  ��  19
  ��  0  18  ��  ��  ��
  ��  ��  0  30  11  ��
  15  32  50  0  ��  34
  ��  4  22  16  0  13
  ��  ��  12  ��  ��  0

��C��Ϊ�м䶥��
(A,B)·������17���滻Ϊ(A,B,C),(C,B)·������100034����
(A,D)·������99999���滻Ϊ(A,B,C),(C,D)·������65���ǣ�d03=65��p03=2
(A,E)·������99999���滻Ϊ(A,B,C),(C,E)·������46���ǣ�d04=46��p04=2
(A,F)·������19���滻Ϊ(A,B,C),(C,F)·������100034����
(B,A)·������99999���滻Ϊ(B,C),(C,A)·������100017����
(B,D)·������99999���滻Ϊ(B,C),(C,D)·������48���ǣ�d13=48��p13=2
(B,E)·������99999���滻Ϊ(B,C),(C,E)·������29���ǣ�d14=29��p14=2
(B,F)·������99999���滻Ϊ(B,C),(C,F)·������100017����
(D,A)·������15���滻Ϊ(D,A,B,C),(C,A)·������100049����
(D,A,B)·������32���滻Ϊ(D,A,B,C),(C,B)·������100049����
(D,E)·������99999���滻Ϊ(D,A,B,C),(C,E)·������61���ǣ�d34=61��p34=2
(D,A,F)·������34���滻Ϊ(D,A,B,C),(C,F)·������100049����
(E,A)·������99999���滻Ϊ(E,B,C),(C,A)·������100021����
(E,B)·������4���滻Ϊ(E,B,C),(C,B)·������100021����
(E,D)·������16���滻Ϊ(E,B,C),(C,D)·������52����
(E,F)·������13���滻Ϊ(E,B,C),(C,F)·������100021����
(F,A)·������99999���滻Ϊ(F,C),(C,A)·������100011����
(F,B)·������99999���滻Ϊ(F,C),(C,B)·������100011����
(F,D)·������99999���滻Ϊ(F,C),(C,D)·������42���ǣ�d53=42��p53=2
(F,E)·������99999���滻Ϊ(F,C),(C,E)·������23���ǣ�d54=23��p54=2
path����
  -1  0  1  2  2  0
  -1  -1  1  2  2  -1
  -1  -1  -1  2  2  -1
  3  0  1  -1  2  0
  -1  4  1  4  -1  4
  -1  -1  5  2  2  -1
dist����
  0  17  35  65  46  19
  ��  0  18  48  29  ��
  ��  ��  0  30  11  ��
  15  32  50  0  61  34
  ��  4  22  16  0  13
  ��  ��  12  42  23  0

��D��Ϊ�м䶥��
(A,B)·������17���滻Ϊ(A,B,C,D),(D,A,B)·������97����
(A,B,C)·������35���滻Ϊ(A,B,C,D),(D,A,B,C)·������115����
(A,B,C,E)·������46���滻Ϊ(A,B,C,D),(D,A,B,C,E)·������126����
(A,F)·������19���滻Ϊ(A,B,C,D),(D,A,F)·������99����
(B,A)·������99999���滻Ϊ(B,C,D),(D,A)·������63���ǣ�d10=63��p10=3
(B,C)·������18���滻Ϊ(B,C,D),(D,A,B,C)·������98����
(B,C,E)·������29���滻Ϊ(B,C,D),(D,A,B,C,E)·������109����
(B,F)·������99999���滻Ϊ(B,C,D),(D,A,F)·������82���ǣ�d15=82��p15=0
(C,A)·������99999���滻Ϊ(C,D),(D,A)·������45���ǣ�d20=45��p20=3
(C,B)·������99999���滻Ϊ(C,D),(D,A,B)·������62���ǣ�d21=62��p21=0
(C,E)·������11���滻Ϊ(C,D),(D,A,B,C,E)·������91����
(C,F)·������99999���滻Ϊ(C,D),(D,A,F)·������64���ǣ�d25=64��p25=0
(E,A)·������99999���滻Ϊ(E,D),(D,A)·������31���ǣ�d40=31��p40=3
(E,B)·������4���滻Ϊ(E,D),(D,A,B)·������48����
(E,B,C)·������22���滻Ϊ(E,D),(D,A,B,C)·������66����
(E,F)·������13���滻Ϊ(E,D),(D,A,F)·������50����
(F,A)·������99999���滻Ϊ(F,C,D),(D,A)·������57���ǣ�d50=57��p50=3
(F,B)·������99999���滻Ϊ(F,C,D),(D,A,B)·������74���ǣ�d51=74��p51=0
(F,C)·������12���滻Ϊ(F,C,D),(D,A,B,C)·������92����
(F,C,E)·������23���滻Ϊ(F,C,D),(D,A,B,C,E)·������103����
path����
  -1  0  1  2  2  0
  3  -1  1  2  2  0
  3  0  -1  2  2  0
  3  0  1  -1  2  0
  3  4  1  4  -1  4
  3  0  5  2  2  -1
dist����
  0  17  35  65  46  19
  63  0  18  48  29  82
  45  62  0  30  11  64
  15  32  50  0  61  34
  31  4  22  16  0  13
  57  74  12  42  23  0

��E��Ϊ�м䶥��
(A,B)·������17���滻Ϊ(A,B,C,E),(E,B)·������50����
(A,B,C)·������35���滻Ϊ(A,B,C,E),(E,B,C)·������68����
(A,B,C,D)·������65���滻Ϊ(A,B,C,E),(E,D)·������62���ǣ�d03=62��p03=4
(A,F)·������19���滻Ϊ(A,B,C,E),(E,F)·������59����
(B,C,D,A)·������63���滻Ϊ(B,C,E),(E,D,A)·������60���ǣ�d10=60��p10=3
(B,C)·������18���滻Ϊ(B,C,E),(E,B,C)·������51����
(B,C,D)·������48���滻Ϊ(B,C,E),(E,D)·������45���ǣ�d13=45��p13=4
(B,C,E,D,A,F)·������82���滻Ϊ(B,C,E),(E,F)·������42���ǣ�d15=42��p15=4
(C,D,A)·������45���滻Ϊ(C,E),(E,D,A)·������42���ǣ�d20=42��p20=3
(C,D,A,B)·������62���滻Ϊ(C,E),(E,B)·������15���ǣ�d21=15��p21=4
(C,D)·������30���滻Ϊ(C,E),(E,D)·������27���ǣ�d23=27��p23=4
(C,E,D,A,F)·������64���滻Ϊ(C,E),(E,F)·������24���ǣ�d25=24��p25=4
(D,A)·������15���滻Ϊ(D,A,B,C,E),(E,D,A)·������92����
(D,A,B)·������32���滻Ϊ(D,A,B,C,E),(E,B)·������65����
(D,A,B,C)·������50���滻Ϊ(D,A,B,C,E),(E,B,C)·������83����
(D,A,F)·������34���滻Ϊ(D,A,B,C,E),(E,F)·������74����
(F,C,D,A)·������57���滻Ϊ(F,C,E),(E,D,A)·������54���ǣ�d50=54��p50=3
(F,C,D,A,B)·������74���滻Ϊ(F,C,E),(E,B)·������27���ǣ�d51=27��p51=4
(F,C)·������12���滻Ϊ(F,C,E),(E,B,C)·������45����
(F,C,D)·������42���滻Ϊ(F,C,E),(E,D)·������39���ǣ�d53=39��p53=4
path����
  -1  0  1  4  2  0
  3  -1  1  4  2  4
  3  4  -1  4  2  4
  3  0  1  -1  2  0
  3  4  1  4  -1  4
  3  4  5  4  2  -1
dist����
  0  17  35  62  46  19
  60  0  18  45  29  42
  42  15  0  27  11  24
  15  32  50  0  61  34
  31  4  22  16  0  13
  54  27  12  39  23  0

��F��Ϊ�м䶥��
(A,B)·������17���滻Ϊ(A,F),(F,C,E,B)·������46����
(A,B,C)·������35���滻Ϊ(A,F),(F,C)·������31���ǣ�d02=31��p02=5
(A,F,C,E,D)·������62���滻Ϊ(A,F),(F,C,E,D)·������58���ǣ�d03=58��p03=4
(A,F,C,E)·������46���滻Ϊ(A,F),(F,C,E)·������42���ǣ�d04=42��p04=2
(B,C,E,D,A)·������60���滻Ϊ(B,C,E,F),(F,C,E,D,A)·������96����
(B,C)·������18���滻Ϊ(B,C,E,F),(F,C)·������54����
(B,C,E,D)·������45���滻Ϊ(B,C,E,F),(F,C,E,D)·������81����
(B,C,E)·������29���滻Ϊ(B,C,E,F),(F,C,E)·������65����
(C,E,D,A)·������42���滻Ϊ(C,E,F),(F,C,E,D,A)·������78����
(C,E,B)·������15���滻Ϊ(C,E,F),(F,C,E,B)·������51����
(C,E,D)·������27���滻Ϊ(C,E,F),(F,C,E,D)·������63����
(C,E)·������11���滻Ϊ(C,E,F),(F,C,E)·������47����
(D,A)·������15���滻Ϊ(D,A,F),(F,C,E,D,A)·������88����
(D,A,B)·������32���滻Ϊ(D,A,F),(F,C,E,B)·������61����
(D,A,B,C)·������50���滻Ϊ(D,A,F),(F,C)·������46���ǣ�d32=46��p32=5
(D,A,F,C,E)·������61���滻Ϊ(D,A,F),(F,C,E)·������57���ǣ�d34=57��p34=2
(E,D,A)·������31���滻Ϊ(E,F),(F,C,E,D,A)·������67����
(E,B)·������4���滻Ϊ(E,F),(F,C,E,B)·������40����
(E,B,C)·������22���滻Ϊ(E,F),(F,C)·������25����
(E,D)·������16���滻Ϊ(E,F),(F,C,E,D)·������52����
path����
  -1  0  5  4  2  0
  3  -1  1  4  2  4
  3  4  -1  4  2  4
  3  0  5  -1  2  0
  3  4  1  4  -1  4
  3  4  5  4  2  -1
dist����
  0  17  31  58  42  19
  60  0  18  45  29  42
  42  15  0  27  11  24
  15  32  46  0  57  34
  31  4  22  16  0  13
  54  27  12  39  23  0

ÿ�Զ��������·�����£�
(A,B)����Ϊ17
(A,F,C)����Ϊ31
(A,F,C,E,D)����Ϊ58
(A,F,C,E)����Ϊ42
(A,F)����Ϊ19
(B,C,E,D,A)����Ϊ60
(B,C)����Ϊ18
(B,C,E,D)����Ϊ45
(B,C,E)����Ϊ29
(B,C,E,F)����Ϊ42
(C,E,D,A)����Ϊ42
(C,E,B)����Ϊ15
(C,E,D)����Ϊ27
(C,E)����Ϊ11
(C,E,F)����Ϊ24
(D,A)����Ϊ15
(D,A,B)����Ϊ32
(D,A,F,C)����Ϊ46
(D,A,F,C,E)����Ϊ57
(D,A,F)����Ϊ34
(E,D,A)����Ϊ31
(E,B)����Ϊ4
(E,B,C)����Ϊ22
(E,D)����Ϊ16
(E,F)����Ϊ13
(F,C,E,D,A)����Ϊ54
(F,C,E,B)����Ϊ27
(F,C)����Ϊ12
(F,C,E,D)����Ϊ39
(F,C,E)����Ϊ23

*/
