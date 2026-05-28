import java.util.Scanner;

public class TextSteganographySystem {

    // Encode Message
    public static String encode(String coverText, String secretMessage) {

        StringBuilder encoded = new StringBuilder();

        for (int i = 0; i < secretMessage.length(); i++) {

            encoded.append(coverText.charAt(i));
            encoded.append(secretMessage.charAt(i));
        }

        encoded.append(coverText.substring(secretMessage.length()));

        return encoded.toString();
    }

    // Decode Message
    public static String decode(String encodedText, int secretLength) {

        StringBuilder secret = new StringBuilder();

        for (int i = 1; i < secretLength * 2; i += 2) {

            secret.append(encodedText.charAt(i));
        }

        return secret.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Cover Text: ");
        String coverText = sc.nextLine();

        System.out.print("Enter Secret Message: ");
        String secretMessage = sc.nextLine();

        if (secretMessage.length() > coverText.length()) {

            System.out.println("Secret message is too long!");
            return;
        }

        // Encoding
        String encodedText = encode(coverText, secretMessage);

        System.out.println("\nEncoded Text:");
        System.out.println(encodedText);

        // Decoding
        String decodedText = decode(encodedText, secretMessage.length());

        System.out.println("\nDecoded Secret Message:");
        System.out.println(decodedText);

        sc.close();
    }
}