package com.dilmurod.clickup.component;


//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    CustomFieldRepository customFieldRepository;
//
//    @Value("${spring.sql.init.mode}")
//    String initMode;
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (initMode.equals("always")) {
//
//            EnumFields[] fields = EnumFields.values();
//
//            customFieldRepository.save(new CustomField(EnumFields.DROPDOWN.));
//            customFieldRepository.save(new CustomField(EnumFields.NUMBER.name()));
//            customFieldRepository.save(new CustomField(EnumFields.RATING.name()));
//            customFieldRepository.save(new CustomField(EnumFields.TEXT.name()));
//        }
//
//    }
//}
