

import com.way361.structure.list.linked.SortedSinglyLinkedList;

//7.2.2   ͼ���ڽӱ��ʾ��ʵ��
//�����Ԫ����

//�����Ԫ��
public class Vertex<T> {
    public T data;                                         //����������
    public SortedSinglyLinkedList<Edge> adjlink;           //�ö���ıߵ�����
    
    public Vertex(T data) {
        this.data = data;
        this.adjlink = new SortedSinglyLinkedList<Edge>(); //���춥��ʱ�����յ�����
    }
    
    //���ض��㼰��ߵ�����������ַ���
    public String toString() {
        return "\n"+this.data.toString()+"��"+this.adjlink.toString();
    }
}
/*���У�ǳ����
 *     public Vertex(T data, SortedSinglyLinkedList<Edge> adjlink)
{
    this.data = data;
    this.adjlink = adjlink;       //���ø�ֵ
}
    public Vertex(T data)
    {
        this(data, new SortedSinglyLinkedList<Edge>());   //������ʱ�����յ�����
    }
*/
