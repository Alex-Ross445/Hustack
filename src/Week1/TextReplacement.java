package Week1;


//Week 1 - Text Replacement
//        Description
//        Cho văn bản T và 2 mẫu P1, P2 đều là các xâu ký tự (không chứa ký tự xuống dòng, độ dài không vượt quá 1000). Hãy thay thế các xâu P1 trong T bằng xâu P2.
//        Dữ liệu
//        · Dòng 1: xâu P1
//        · Dòng 2: xâu P2
//        · Dòng 3: văn bản T
//        Kết quả:
//        · Ghi văn bản T sau khi thay thế
//        Ví dụ
//        Dữ liệu
//        AI
//        Artificial Intelligence
//        Recently, AI is a key technology. AI enable efficient operations in many fields.
//        Kết quả
//        Recently, Artificial Intelligence is a key technology. Artificial Intelligence enable efficient operations in many fields.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextReplacement {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Đọc các chuỗi P1, P2 và văn bản T
        String P1 = br.readLine().trim();
        String P2 = br.readLine().trim();
        String T = br.readLine();

        // Thay thế P1 trong T bằng P2
        String result = T.replace(P1, P2);

        // In ra kết quả
        System.out.println(result);
    }
}