package com.example.tosansimplerest.backend.utilities;

public class Validator {
    private static class NationalIdValidator {

        private static boolean validate(Long nationalId) {
            // 4120128431
            if (String.valueOf(nationalId).length() != 10)
                return false;
            long temp = nationalId;
            int a = 0;
            int position = 1;
            int c;
            while (temp > 0) {
                if (position != 1)
                    a += temp % 10 * position;
                temp /= 10;
                position++;
            }
            c = a % 11 < 2 ? a % 11 : 11 - a % 11;
            return c == nationalId % 10;
        }
    }

    public static boolean validateNationalId(Long nationalId) {
        return nationalId != null && NationalIdValidator.validate(nationalId);
    }
}
