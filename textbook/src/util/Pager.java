package util;

/**
 * Title: Pager
 * Description: ��ҳ����
 */
public class Pager {
	
	//��׼�б��ҳ�����ں�̨
	public static String getPagerNormal(int total, int pagesize, int pagenum,String pageurl,String info) {
		int count = total / pagesize;
		if (total % pagesize > 0) {
			count++;
		}
		if(pageurl.indexOf("?")>-1){
			pageurl = pageurl + "&";
		}else{
			pageurl = pageurl + "?";
		}
		StringBuffer buf = new StringBuffer();
		buf.append(info+"&nbsp;&nbsp;");
		buf.append(pagenum+"/"+ count +"&nbsp;&nbsp;");
		if (pagenum == 1) {
			buf.append("<SPAN style='color:#CCCCCC'>����ҳ��</SPAN><SPAN style='color:#CCCCCC'>����һҳ��</SPAN>&nbsp;&nbsp;");
		} else {
			buf.append("��<a href='" + pageurl + "pagenum=1'>��ҳ</a>����<a href='" + pageurl + "pagenum=" + (pagenum - 1)
					+ "' >��һҳ</a>��");
		}
		int bound1 = ((pagenum - 2) <= 0) ? 1 : (pagenum - 2);
		int bound2 = ((pagenum + 2) >= count) ? count : (pagenum + 2);
		for (int i = bound1; i <= bound2; i++) {
			if (i == pagenum) {
				buf.append("<SPAN style='color:#FF0000'>" + i
						+ "</SPAN>&nbsp;&nbsp;");
			} else {
				buf.append("<a href='" + pageurl + "pagenum=" + i + "'>" + i
						+ "</a>&nbsp;&nbsp;");
			}
		}
		if (bound2 < count) {
			buf.append("<SPAN>...</SPAN>");
		}
		if (pagenum == count||count==0) {
			buf.append("<SPAN style='color:#CCCCCC'>����һҳ��</SPAN><SPAN style='color:#CCCCCC'>��βҳ��</SPAN>");
		} else {
			buf.append("��<a href='" + pageurl + "pagenum=" + (pagenum + 1)
					+ "'>��һҳ</a>����<a href='" + pageurl + "pagenum=" + count
					+ "'>βҳ</a>��");
		}
		return buf.toString();
	}
}
