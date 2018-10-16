package com.codenotfound.crnk.domain.repository;


import com.codenotfound.crnk.domain.model.Address;
import com.codenotfound.crnk.domain.model.Library;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class LibraryToAddressRepositoryImpl extends RelationshipRepositoryBase<Library, Long, Address, Long> {
    protected LibraryToAddressRepositoryImpl(Class<Library> sourceResourceClass, Class<Address> targetResourceClass) {
        super(sourceResourceClass, targetResourceClass);
    }
    public LibraryToAddressRepositoryImpl() {
        super(Library.class, Address.class);
    }

}
