package de.trion.training.api;

import java.util.ArrayList;
import java.util.List;

public class ValidationDto {
   private String code;
   private List<FieldErrorDto> fieldErrors = new ArrayList<>();

   public void addFieldError(FieldErrorDto fieldErrorDto) {
      fieldErrors.add(fieldErrorDto);
   }
   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public List<FieldErrorDto> getFieldErrors() {
      return fieldErrors;
   }

   public void setFieldErrors(List<FieldErrorDto> fieldErrors) {
      this.fieldErrors = fieldErrors;
   }
}
