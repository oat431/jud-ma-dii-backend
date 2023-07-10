package panomete.judsue.bill.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import panomete.judsue.bill.entity.ItemList;
import panomete.judsue.bill.payload.request.ItemListRequest;

@Service
@RequiredArgsConstructor
public class ItemListServiceImpl implements ItemListService {
    @Override
    public ItemList createItemList(ItemListRequest request) {
        return null;
    }

    @Override
    public ItemList updateItemList(Long id, ItemListRequest request) {
        return null;
    }

    @Override
    public ItemList deleteItemList(Long id) {
        return null;
    }
}
