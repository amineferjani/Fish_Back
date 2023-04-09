package tn.fmass.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.fmass.entities.Manager;
import tn.fmass.entities.Sayed;
import tn.fmass.exceptions.ResourceNotFoundException;
import tn.fmass.repos.UtilisateurRepo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceTest {

    @Mock private UtilisateurRepo utilisateurRepo;
    private UtilisateurService utilisateurService;
    @BeforeEach
    void setUp() {
        utilisateurService=new UtilisateurService(utilisateurRepo);
    }

    @Test
    void addSayed() {
        //given
        Sayed sayed=  new Sayed();
        sayed.setId(1L);sayed.setEmail("amine@gmail.com");
        sayed.setName("Amine Ferjani");sayed.setTelephone("20202020");
        //when
        utilisateurService.addSayed(sayed);
        //then
        ArgumentCaptor<Sayed> sayedArgumentCaptor=
                ArgumentCaptor.forClass(Sayed.class);
        verify(utilisateurRepo).save(sayedArgumentCaptor.capture());
        Sayed s=sayedArgumentCaptor.getValue();
        assertThat(s).isEqualTo(sayed);
    }

    @Test
    void addManager() {
        //given
        Manager manager=  new Manager();
        manager.setId(1L);manager.setEmail("amine@gmail.com");
        manager.setName("Amine Ferjani");manager.setTelephone("20202020");
        //when
        utilisateurService.addManager(manager);
        //then
        ArgumentCaptor<Manager> managerArgumentCaptor=
                ArgumentCaptor.forClass(Manager.class);
        verify(utilisateurRepo).save(managerArgumentCaptor.capture());
        Manager m=managerArgumentCaptor.getValue();
        assertThat(m).isEqualTo(manager);
    }

    @Test
    void getAllSayed() {
        //when
        utilisateurService.getAllSayed();
        //then
        verify(utilisateurRepo).findAllSayed();
    }

    @Test
    void getAllManager() {
        //when
        utilisateurService.getAllManager();
        //then
        verify(utilisateurRepo).findAllManager();
    }

    @Test
    void deleteUserWhenExist() {
        //given
        Long id=1L;
        given(utilisateurRepo.existsById(id)).willReturn(true);
        //when
        utilisateurService.deleteUser(id);
        //then
        ArgumentCaptor<Long> longArgumentCaptor=
                ArgumentCaptor.forClass(Long.class);
        verify(utilisateurRepo).deleteById(longArgumentCaptor.capture());
        Long l=longArgumentCaptor.getValue();
        assertThat(l).isEqualTo(id);
    }

    @Test
    void deleteUserWhenDoesNotExist() {
        //given
        Long id=1L;
        given(utilisateurRepo.existsById(id)).willReturn(false);
        //when
        //then
        assertThatThrownBy(()->utilisateurService.deleteUser(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Utilisateur avec id "+id+" inexistant !");
    }

    @Test
    void updateSayedWhenExist() {
        //given
        Long id=1L;
        Sayed sayed=  new Sayed();
        sayed.setId(1L);sayed.setEmail("amine@gmail.com");
        sayed.setName("Amine Ferjani");sayed.setTelephone("20202020");
        given(utilisateurRepo.existsById(id)).willReturn(true);
        //when
        utilisateurService.updateSayed(sayed,id);
        //then
        ArgumentCaptor<Sayed> sayedArgumentCaptor=
                ArgumentCaptor.forClass(Sayed.class);
        verify(utilisateurRepo).save(sayedArgumentCaptor.capture());
        Sayed s=sayedArgumentCaptor.getValue();
        assertThat(s).isEqualTo(sayed);
        assertThat(id).isEqualTo(sayed.getId());
    }
    @Test
    void updateSayedWhenDoesNotExist() {
        //given
        Long id=1L;
        Sayed sayed=  new Sayed();
        sayed.setId(1L);sayed.setEmail("amine@gmail.com");
        sayed.setName("Amine Ferjani");sayed.setTelephone("20202020");
        given(utilisateurRepo.existsById(id)).willReturn(false);
        //when
        //then
        assertThatThrownBy(()->utilisateurService.updateSayed(sayed,id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Utilisateur avec id "+id+" inexistant !");
    }

    @Test
    void updateManagerWhenExist() {
        //given
        Long id=1L;
        Manager manager=  new Manager();
        manager.setId(1L);manager.setEmail("amine@gmail.com");
        manager.setName("Amine Ferjani");manager.setTelephone("20202020");
        given(utilisateurRepo.existsById(id)).willReturn(true);
        //when
        utilisateurService.updateManager(manager,id);
        //then
        ArgumentCaptor<Manager> managerArgumentCaptor=
                ArgumentCaptor.forClass(Manager.class);
        verify(utilisateurRepo).save(managerArgumentCaptor.capture());
        Manager m=managerArgumentCaptor.getValue();
        assertThat(m).isEqualTo(manager);
        assertThat(id).isEqualTo(manager.getId());
    }
    @Test
    void updateManagerWhenDoesNotExist() {
        //given
        Long id=1L;
        Manager manager=  new Manager();
        manager.setId(1L);manager.setEmail("amine@gmail.com");
        manager.setName("Amine Ferjani");manager.setTelephone("20202020");
        given(utilisateurRepo.existsById(id)).willReturn(false);
        //when
        //then
        assertThatThrownBy(()->utilisateurService.updateManager(manager,id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Utilisateur avec id "+id+" inexistant !");

    }
    @Test
    void auth() {
    }
}