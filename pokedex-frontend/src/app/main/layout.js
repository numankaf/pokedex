import AppTopbar from "@/components/layout/AppTopbar";

export default function MainLayout({children}) {
    return (
        <>
            <AppTopbar></AppTopbar>
            {children}
        </>
    )
}
