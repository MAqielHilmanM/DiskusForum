/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import home.model.HomeModel;
import auth.AuthController;
import base.BaseController;
import home.comment.CommentRowModel;
import home.community.CommunityRowModel;
import home.com_thread.CommunityThreadRowModel;
import home.hot_thread.HotThreadRowModel;
import home.member.MemberRowModel;
import home.model.HomeCommunityModel;
import home.model.HomeHotThreadModel;
import home.model.HomeMyThreadModel;
import home.model.HomeProfileModel;
import home.model.SubHomeCommunityModel;
import home.model.SubHomeHotThreadModel;
import home.model.SubHomeMyThreadModel;
import home.my_thread.MyThreadRowModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.View;
import utils.Constant;
import utils.Helper;

/**
 *
 * @author maqielhm
 */
public class HomeController implements BaseController {

    private HomeModel mModel;
    private HomeView mView;
    private String mId;
    private String mIdThread;

    public HomeController(String id) {
        mModel = new HomeModel();
        mView = new HomeView();
        mView.setVisible(true);
        mId = id;
        Constant.USER_ID = id;
        initComponent();
    }

    @Override
    public void initComponent() {
        mModel = mModel.findSingleBy(mId);
        loadListHomeL();
        mView.changeMenu(MenuType.MENU_HOME);

        if (mModel != null) {
            mView.setName(Helper.convertNameToShortName(mModel.getmName(), 10));
        }

        mView.addPnlMnHomeAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadListHomeL();
                mView.changeMenu(MenuType.MENU_HOME);
            }
        });
        mView.addPnlMnCommunityAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadListCommunityL();
                loadListCommunityRMember();
                loadListCommunityRThread();
                mView.changeMenu(MenuType.MENU_COMMUNITY);
            }
        });
        mView.addPnlMnMyThreadAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadListMyThreadL();
                mView.changeMenu(MenuType.MENU_MY_THREAD);
            }
        });
        mView.addPnlLogOutAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AuthController();
                mView.dispose();
            }
        });
        mView.addPnlProfileAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadProfile();
                mView.changeMenu(MenuType.MENU_PROFILE);
            }
        });

        mView.addTFSubHomeLDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!mView.getTFSubHomeL().equals("") && !mView.getTFSubHomeL().equals(Constant.DEFAULT_SUBHOME_SEARCH_TEXT)) {
                    loadListHomeL(mView.getTFSubHomeL());
                } else {
                    loadListHomeL();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!mView.getTFSubHomeL().equals("")
                        && !mView.getTFSubHomeL().equals(Constant.DEFAULT_SUBHOME_SEARCH_TEXT)) {
                    loadListHomeL(mView.getTFSubHomeL());
                } else {
                    loadListHomeL();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        mView.addTFSubCommunityLDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!mView.getTFSubCommunityL().equals("")
                        && !mView.getTFSubCommunityL().equals(Constant.DEFAULT_SUBCOMMUNITY_SEARCH_TEXT)) {
                    loadListCommunityL(mView.getTFSubCommunityL());
                } else {
                    loadListCommunityL();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!mView.getTFSubCommunityL().equals("")
                        && !mView.getTFSubCommunityL().equals(Constant.DEFAULT_SUBCOMMUNITY_SEARCH_TEXT)) {
                    loadListCommunityL(mView.getTFSubCommunityL());
                } else {
                    loadListCommunityL();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        mView.addLstSubHomeLListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mIdThread = mModel.getmHotThreadModel().getHotThreadLists().get(mView.getLstSubHomeLIndex()).getId();
                loadListHomeR(mIdThread);
            }
        });

        mView.setLblSubHomeRLikeAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SubHomeHotThreadModel model = mModel.getmSubHomeHotThreadHashMap(mIdThread);
                SubHomeHotThreadModel.RequestLike reqLike = model.new RequestLike(mIdThread, model.getmIdCommunity(), model.getmIdMember(), true);
                if (model.insert(reqLike)) {
                    mView.showMessage("Berhasil Like", "Status", 1);
                } else {
                    mView.showMessage("Gagal Like", "Status", 0);
                }
            }
        });

        mView.setLblSubHomeRSendAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SubHomeHotThreadModel model = mModel.getmSubHomeHotThreadHashMap(mIdThread);

                SubHomeHotThreadModel.RequestComment reqComment = model.new RequestComment(
                        mIdThread,
                        model.getmIdCommunity(),
                        model.getmIdMember(),
                        mView.getTfSubHomeRComment());
                if (model.insert(reqComment)) {
                    mView.showMessage("Berhasil Menambahkan Comment", "Status", 1);

                    loadListHomeR(mIdThread);
                } else {
                    mView.showMessage("Gagal Menambahkan Comment", "Status", 0);
                }
            }

        });
    }

    public void loadProfile() {
        HomeProfileModel profileModel = new HomeProfileModel();
        profileModel = profileModel.findSingleBy(mId);

        mView.setLblSubProfileComCount(profileModel.getmCommCount());
        mView.setLblSubProfileCountry(profileModel.getmCountry());
        if (profileModel.getmSince().equals("0")) {
            mView.setLblSubProfileDate(profileModel.getmDate());
        } else {
            mView.setLblSubProfileDate(profileModel.getmDate() + "(" + profileModel.getmSince() + " years )");
        }
        mView.setLblSubProfileEmail(profileModel.getmEmail());
        mView.setLblSubProfileGender(profileModel.getmGender());
        mView.setLblSubProfileName(profileModel.getmName());
        mView.setLblSubProfilePostCount(profileModel.getmPostCount());
        mView.setLblSubProfileTelp(profileModel.getmTelp());
        mView.setLblSubProfileTitle(profileModel.getmTitle());
        mView.setLblSubProfileUsername(profileModel.getmUsername());
    }

    public void loadListHomeL() {
        DefaultListModel<HotThreadRowModel> dlm = new DefaultListModel<>();
        mModel.getmHotThreadModel().setHotThreadLists(new HomeHotThreadModel().findAll());
        for (HotThreadRowModel data : mModel.getmHotThreadModel().getHotThreadLists()) {
            dlm.addElement(data);
        }
        mView.setLstSubHomeL(dlm);
    }

    public void loadListHomeL(String query) {
        DefaultListModel<HotThreadRowModel> dlm = new DefaultListModel<>();
        mModel.getmHotThreadModel().setHotThreadLists(new HomeHotThreadModel().findAllBy(query));
        for (HotThreadRowModel data : mModel.getmHotThreadModel().getHotThreadLists()) {
            dlm.addElement(data);
        }
        mView.setLstSubHomeL(dlm);
    }

    public void loadListMyThreadL() {
        DefaultListModel<MyThreadRowModel> dlm = new DefaultListModel<>();
        mModel.getmMyThreadModel().setmMyThreadLists(new HomeMyThreadModel().findAll());
        for (MyThreadRowModel data : mModel.getmMyThreadModel().getmMyThreadLists()) {
            dlm.addElement(data);
        }
        mView.setLstSubMyThreadL(dlm);
    }

    public void loadListMyThreadL(String query) {
        DefaultListModel<MyThreadRowModel> dlm = new DefaultListModel<>();
        mModel.getmMyThreadModel().setmMyThreadLists(new HomeMyThreadModel().findAllBy(query));
        for (MyThreadRowModel data : mModel.getmMyThreadModel().getmMyThreadLists()) {
            dlm.addElement(data);
        }
        mView.setLstSubMyThreadL(dlm);
    }

    public void loadListCommunityL() {
        DefaultListModel<CommunityRowModel> dlm = new DefaultListModel<>();
        mModel.getmCommunityModel().setmCommunityLists(new HomeCommunityModel().findAll());
        for (CommunityRowModel data : mModel.getmCommunityModel().getmCommunityLists()) {
            dlm.addElement(data);
        }
        mView.setLstSubCommunityL(dlm);
    }

    public void loadListCommunityL(String query) {
        DefaultListModel<CommunityRowModel> dlm = new DefaultListModel<>();
        mModel.getmCommunityModel().setmCommunityLists(new HomeCommunityModel().findAllBy(query));
        for (CommunityRowModel data : mModel.getmCommunityModel().getmCommunityLists()) {
            dlm.addElement(data);
        }
        mView.setLstSubCommunityL(dlm);
    }

    public void loadListHomeR(String id) {
        DefaultListModel<CommentRowModel> dlm = new DefaultListModel<>();
        SubHomeHotThreadModel data = mModel.getmSubHomeHotThreadHashMap(id);
        if (data == null) {
            data = new SubHomeHotThreadModel().findSingleBy(id);
            if (data != null) {
                mModel.setmSubHomeHotThreadHashMap(id, data);
            }
        }

        if (data != null) {
            mView.setSubHomeRAuthor(
                    data.getmAuthorName(),
                    data.getmAuthorPic(),
                    data.getmAuthorTitle(),
                    data.getmAuthorPosition(),
                    data.getmAuthorCommunity(),
                    data.getmAuthorPostCount(),
                    data.getmAuthorStatus());
            mView.setSubHomeRThread(
                    data.getmTitle(),
                    data.getmTrustAvg(),
                    String.valueOf(data.getmTrustCount()),
                    String.valueOf(data.getmTrustTotal()),
                    Helper.convertDateToString(data.getmDate()),
                    Helper.convertTimeToString(data.getmDate()),
                    data.getmBody());

            if (data.getmCommentLists().size() > 0) {
                data.getmCommentLists().forEach((t) -> {
                    dlm.addElement(t);
                });

            }
        }

        mView.setLstSubHomeR(dlm);

    }

    public void loadListMyThreadR(String id) {
        DefaultListModel<CommentRowModel> dlm = new DefaultListModel<>();
        SubHomeMyThreadModel data = mModel.getmSubHomeMyThreadHashMap(id);
        if (data == null) {
            data = new SubHomeMyThreadModel().findSingleBy(id);
            if (data != null) {
                mModel.setmSubHomeMyThreadHashMap(id, data);
            }
        }

        if (data != null) {
            mView.setSubMyThreadR(
                    data.getmTitle(),
                    data.getmTrustAvg(),
                    String.valueOf(data.getmTrustCount()),
                    String.valueOf(data.getmTrustTotal()),
                    Helper.convertDateToString(data.getmDate()),
                    Helper.convertTimeToString(data.getmDate()),
                    data.getmBody());

            if (data.getmCommentLists().size() > 0) {
                data.getmCommentLists().forEach((t) -> {
                    dlm.addElement(t);
                });

            }
        }

        mView.setLstSubMyThreadR(dlm);
    }

    public void loadListCommunityRThread() {
        DefaultListModel<CommunityThreadRowModel> dlm = new DefaultListModel<>();
        dlm.addElement(new CommunityThreadRowModel("", "10 Jan 18", "Lorem Ipsum", "..."));
        dlm.addElement(new CommunityThreadRowModel("", "10 Jan 18", "Lorem Ipsum", "..."));
        mView.setLstSubCommunityRThread(dlm);
    }

    public void loadListCommunityRMember() {
        DefaultListModel<MemberRowModel> dlm = new DefaultListModel<>();
        dlm.addElement(new MemberRowModel("", "John Doe", "Veteran at", "Lorem Ipsum", "Lorem Community", "12", "Online"));
        dlm.addElement(new MemberRowModel("", "John Doe", "Veteran at", "Lorem Ipsum", "Lorem Community", "12", "Online"));
        dlm.addElement(new MemberRowModel("", "John Doe", "Veteran at", "Lorem Ipsum", "Lorem Community", "12", "Online"));
        mView.setLstSubCommunityRMember(dlm);
    }
}
