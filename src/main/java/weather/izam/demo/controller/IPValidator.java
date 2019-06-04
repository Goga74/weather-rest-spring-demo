package weather.izam.demo.controller;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IPValidator implements ConstraintValidator<IPConstraint, String> {

    @Override
    public void initialize(IPConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String ipAddress, ConstraintValidatorContext cxt) {
        // ToDO: add regexp to check IPv4 address syntax
        return ipAddress != null &&
                (ipAddress.length() > 6) && (ipAddress.length() < 16);
    }

}
